package com.tolgakurucay.mynotebooknew.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tolgakurucay.mynotebooknew.data.database.NoteDao
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImp @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val noteDao: NoteDao
) : HomeRepository {
    override suspend fun getAllNotesFromRemote(): Flow<List<NoteModel>> = noteDao.getAllNotes()
    override suspend fun getAllNotesFromLocale(): Flow<List<NoteModel>> = noteDao.getAllNotes()
    override suspend fun updateNoteFromLocale(model: NoteModel): Int? = noteDao.updateNote(model)
    override suspend fun updateNotesFromLocale(list: List<NoteModel>): Int? = noteDao.update(list)
    override suspend fun addNoteToLocale(model: NoteModel): Long = noteDao.addNote(model)
    override suspend fun deleteNoteFromLocale(model: NoteModel): Int = noteDao.deleteNote(model)
    override suspend fun deleteNotesFromLocale(list: List<NoteModel>): Int = noteDao.deleteNotes(list)
    override suspend fun searchNoteByAll(searchText: String): Flow<List<NoteModel>> = noteDao.searchNoteByAll(searchText)
}