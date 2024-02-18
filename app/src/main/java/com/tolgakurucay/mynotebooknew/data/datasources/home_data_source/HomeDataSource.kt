package com.tolgakurucay.mynotebooknew.data.datasources.home_data_source

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tolgakurucay.mynotebooknew.data.database.NoteDao
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.repository.HomeRepository
import com.tolgakurucay.mynotebooknew.util.isNull
import com.tolgakurucay.mynotebooknew.util.serializeToMap
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HomeDataSource @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val noteDao: NoteDao,
) : HomeRepository {
    //Locale Functions
    override suspend fun getAllNotesFromLocale(): Flow<List<NoteModel>> = noteDao.getAllNotes()
    override suspend fun updateNoteFromLocale(model: NoteModel): Int? = noteDao.updateNote(model)
    override suspend fun updateNotesFromLocale(list: List<NoteModel>): Int? =
        noteDao.updateNotes(list)

    override suspend fun addNoteToLocale(model: NoteModel) = noteDao.addNote(model)
    override suspend fun deleteNoteFromLocale(model: NoteModel): Int = noteDao.deleteNote(model)
    override suspend fun deleteNotesFromLocale(list: List<NoteModel>): Int =
        noteDao.deleteNotes(list)

    override suspend fun searchNoteByAll(searchText: String): Flow<List<NoteModel>> =
        noteDao.searchNoteByAll(searchText)

    //Remote Functions
    override suspend fun addNoteToRemote(model: NoteModel): Flow<Boolean> = flow {
        firestore.collection("Notes").document(auth.currentUser!!.uid).collection("Notes")
            .add(model).await()
        emit(true)
    }

    override suspend fun getAllNotesFromRemote(): Flow<List<NoteModel>> = flow {
        firestore.collection("Notes").document(auth.currentUser!!.uid).collection("Notes").get()
            .await()?.let { queryListSnapshot ->
                emit(queryListSnapshot.map { it.toObject(NoteModel::class.java) })
            }
    }

    override suspend fun updateNoteFromRemote(model: NoteModel): Flow<Boolean> = flow {
        val snapshot =
            firestore.collection("Notes").document(auth.currentUser!!.uid).collection("Notes")
                .whereEqualTo("id", model.id).get().await()
        snapshot.documents.first().reference.update(model.serializeToMap()).await()
        emit(true)
    }

    override suspend fun deleteNoteFromRemote(model: NoteModel): Flow<Boolean> = flow {
        val snapshot =
            firestore.collection("Notes").document(auth.currentUser!!.uid).collection("Notes")
                .whereEqualTo("id", model.id).get().await()
        snapshot.documents.first().reference.delete().await()
        emit(true)
    }

    override suspend fun deleteNotesFromRemote(list: List<NoteModel>): Flow<Boolean> = flow {
        firestore.collection("Notes").document(auth.currentUser!!.uid).collection("Notes").get()
            .await()?.let { snapShotList ->
                snapShotList.forEach { snapShot ->
                 if(list.any { it.id ==  snapShot.getDouble("id")?.toInt()}){
                        snapShot.reference.delete()
                    }
                }
                emit(true)
            }
    }

    override suspend fun addNotesToRemote(list: List<NoteModel>): Flow<Boolean> = flow {
        list.forEachIndexed { index,model->
            firestore.collection("Notes").document(auth.currentUser!!.uid).collection("Notes").add(model).await()
            if(index + 1 == list.size) emit(true)
        }

    }

    override suspend fun getUserRights(): Flow<Int> = flow {
        firestore.collection("Right").document(auth.currentUser!!.uid).get().await()?.let {
            val right = it.getDouble("right")?.toInt()
            if(right.isNull()){
                firestore.collection("Right").document(auth.currentUser!!.uid).update("right",0).await()
                emit(0)
            }
            else{
                emit(right!!)
            }
        }
    }

    override suspend fun decreaseUserRights(newRight: Int): Flow<Boolean> = flow {
        firestore.collection("Right").document(auth.currentUser!!.uid).update("right", newRight)
            .await()
        emit(true)
    }

}