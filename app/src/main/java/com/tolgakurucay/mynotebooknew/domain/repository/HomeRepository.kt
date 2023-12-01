package com.tolgakurucay.mynotebooknew.domain.repository

import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    //Get all notes
    suspend fun getAllNotesFromRemote() : Flow<List<NoteModel>>
    suspend fun getAllNotesFromLocale() : Flow<List<NoteModel>>

    //Update notes
    suspend fun updateNoteFromLocale(model: NoteModel): Int?

    //Add note
    suspend fun addNoteToLocale(model: NoteModel)
    suspend fun addNoteToRemote(model: NoteModel)


}