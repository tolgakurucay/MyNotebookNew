package com.tolgakurucay.mynotebooknew.data.datasources.home_data_source

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tolgakurucay.mynotebooknew.data.database.NoteDao
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.util.serializeToMap
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HomeDataSource @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val noteDao: NoteDao,
) {
    //Locale Functions
    fun getAllNotesFromLocale(): Flow<List<NoteModel>> = noteDao.getAllNotes()
    suspend fun updateNoteFromLocale(noteModel: NoteModel): Int? = noteDao.updateNote(noteModel)
    suspend fun updateNotesFromLocale(noteList: List<NoteModel>): Int? =
        noteDao.updateNotes(noteList)

    suspend fun addNoteToLocale(noteModel: NoteModel) = noteDao.addNote(noteModel)
    suspend fun deleteNoteFromLocale(model: NoteModel): Int = noteDao.deleteNote(model)
    suspend fun deleteNotesFromLocale(noteList: List<NoteModel>): Int =
        noteDao.deleteNotes(noteList)

    fun searchNoteByAllKeywords(searchText: String): Flow<List<NoteModel>> =
        noteDao.searchNoteByAll(searchText)

    //Remote Functions
    fun addNoteToRemote(noteModel: NoteModel): Flow<Boolean> = flow {
        firestore.collection("Notes").document(auth.currentUser!!.uid).collection("Notes").add(noteModel).await()
        emit(true)
    }

    fun getAllNotesFromRemote(): Flow<List<NoteModel>> = flow {
        firestore.collection("Notes").document(auth.currentUser!!.uid).collection("Notes").get()
            .await()?.let { queryListSnapshot ->
                emit(queryListSnapshot.map { it.toObject(NoteModel::class.java) })
            }
    }

    fun updateNoteFromRemote(noteModel: NoteModel): Flow<Boolean> = flow {
        val snapshot = firestore.collection("Notes").document(auth.currentUser!!.uid).collection("Notes")
            .whereEqualTo("id", noteModel.id).get().await()
        snapshot.documents.first().reference.update(noteModel.serializeToMap()).await()
        emit(true)
    }

    fun deleteNoteFromRemote(noteModel: NoteModel): Flow<Boolean> = flow {
        val snapshot = firestore.collection("Notes").document(auth.currentUser!!.uid).collection("Notes")
            .whereEqualTo("id", noteModel.id).get().await()
        snapshot.documents.first().reference.delete().await()
        emit(true)
    }

    fun deleteNotesFromRemote(noteList: List<NoteModel>): Flow<Boolean> = flow {
        firestore.collection("Notes").document(auth.currentUser!!.uid).collection("Notes").get()
            .await()?.let { snapShotList ->
            snapShotList.forEach { snapShot ->
                val snapShotModel = snapShot.toObject(NoteModel::class.java)
                if (snapShot.exists() && noteList.contains(snapShotModel)) {
                    snapShot.reference.delete()
                }
            }
            emit(true)
        }
    }


}