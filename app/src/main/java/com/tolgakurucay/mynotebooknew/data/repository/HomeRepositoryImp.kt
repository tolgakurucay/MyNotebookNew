package com.tolgakurucay.mynotebooknew.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tolgakurucay.mynotebooknew.data.database.NoteDao
import com.tolgakurucay.mynotebooknew.domain.base.BaseException
import com.tolgakurucay.mynotebooknew.domain.base.Result
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.repository.HomeRepository
import com.tolgakurucay.mynotebooknew.util.flowService
import com.tolgakurucay.mynotebooknew.util.resultService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImp @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val noteDao: NoteDao
) : HomeRepository {
    override suspend fun getAllNotesFromRemote(): Flow<List<NoteModel>> = noteDao.getAllNotes()
    override suspend fun getAllNotesFromLocale(): Flow<List<NoteModel>> = noteDao.getAllNotes()
    override suspend fun addNoteToLocale(model: NoteModel) = noteDao.addNote(model)
    override suspend fun addNoteToRemote(model: NoteModel) = noteDao.addNote(model)
}