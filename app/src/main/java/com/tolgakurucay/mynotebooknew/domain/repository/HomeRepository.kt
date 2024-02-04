package com.tolgakurucay.mynotebooknew.domain.repository

import android.provider.ContactsContract.CommonDataKinds.Note
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    //Get all notes
    suspend fun getAllNotesFromRemote() : Flow<List<NoteModel>>
    suspend fun getAllNotesFromLocale() : Flow<List<NoteModel>>

    //Update notes
    suspend fun updateNoteFromLocale(model: NoteModel): Int?
    suspend fun updateNotesFromLocale(list: List<NoteModel>): Int?
    suspend fun updateNoteFromRemote(model: NoteModel): Flow<Boolean>

    //Add note
    suspend fun addNoteToLocale(model: NoteModel): Long
    suspend fun addNoteToRemote(model: NoteModel): Flow<Boolean>
    suspend fun addNotesToRemote(list: List<NoteModel>): Flow<Boolean>

    //Delete note
    suspend fun deleteNoteFromLocale(model: NoteModel): Int
    suspend fun deleteNoteFromRemote(model: NoteModel): Flow<Boolean>

    //Delete notes
    suspend fun deleteNotesFromLocale(list: List<NoteModel>): Int
    suspend fun deleteNotesFromRemote(list: List<NoteModel>): Flow<Boolean>

    //Get notes from DB by searchText
    suspend fun searchNoteByAll(searchText: String): Flow<List<NoteModel>>



}