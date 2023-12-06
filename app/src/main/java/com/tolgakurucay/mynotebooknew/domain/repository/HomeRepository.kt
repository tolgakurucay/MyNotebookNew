package com.tolgakurucay.mynotebooknew.domain.repository

import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    //Get all notes
    suspend fun getAllNotesFromRemote() : Flow<List<NoteModel>>
    suspend fun getAllNotesFromLocale() : Flow<List<NoteModel>>

    //Update notes
    suspend fun updateNoteFromLocale(model: NoteModel): Int?
    suspend fun updateNotesFromLocale(list: List<NoteModel>): Int?

    //Add note
    suspend fun addNoteToLocale(model: NoteModel): Long

    //Delete note
    suspend fun deleteNoteFromLocale(model: NoteModel): Int

    //Delete notes
    suspend fun deleteNotesFromLocale(list: List<NoteModel>): Int

    //Get notes from DB by searchText
    suspend fun searchNoteByAll(searchText: String): Flow<List<NoteModel>>



}