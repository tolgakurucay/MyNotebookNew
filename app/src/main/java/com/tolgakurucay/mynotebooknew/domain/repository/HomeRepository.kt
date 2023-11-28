package com.tolgakurucay.mynotebooknew.domain.repository

import com.tolgakurucay.mynotebooknew.domain.base.Result
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    //Get all notes
    suspend fun getAllNotesFromRemote() : Flow<List<NoteModel>>
    suspend fun getAllNotesFromLocale() : Flow<List<NoteModel>>

  /*  //Get all notes ASC
    suspend fun getAllNotesFromLocaleAsc() : Flow<Result<List<NoteModel>>>
    suspend fun getAllNotesFromRemoteAsc() : Flow<Result<List<NoteModel>>
>
    //Get all notes DESC
    suspend fun getAllNotesFromLocaleDesc() : Flow<Result<List<NoteModel>>>
    suspend fun getAllNotesFromRemoteDesc() : Flow<Result<List<NoteModel>>>

    //Search note by title
    suspend fun searchNoteByTitleFromLocale(title: String) : Flow<Result<NoteModel>>
    suspend fun searchNoteByTitleFromRemote(title: String) : Flow<Result<NoteModel>>

    //Search notes by title
    suspend fun searchNotesByTitleFromLocale(title: String) : Flow<Result<List<NoteModel>>>
    suspend fun searchNotesByTitleFromRemote(title: String) : Flow<Result<List<NoteModel>>>

    //Search note by description
    suspend fun searchNoteByDescFromLocale(description: String) : Flow<Result<NoteModel>>
    suspend fun searchNoteByDescFromRemote(description: String) : Flow<Result<NoteModel>>

    //Search notes by description
    suspend fun searchNotesByDescFromLocale(description: String) : Flow<Result<List<NoteModel>>>
    suspend fun searchNotesByDescFromRemote(description: String) : Flow<Result<List<NoteModel>>>

    //Delete note
    suspend fun deleteNoteFromLocale(model: NoteModel) : Flow<Result<Boolean>>
    suspend fun deleteNoteFromRemote(model: NoteModel) : Flow<Result<Boolean>>

    //Delete all notes
    suspend fun deleteAllNotesFromLocale() : Flow<Result<Boolean>>
    suspend fun deleteAllNotesFromRemote() : Flow<Result<Boolean>>

    //Update note
    suspend fun updateNoteFromLocale(model: NoteModel) : Flow<Result<Boolean>>
    suspend fun updateNoteFromRemote(model: NoteModel) : Flow<Result<Boolean>>*/

    //Add note
    suspend fun addNoteToLocale(model: NoteModel)
    suspend fun addNoteToRemote(model: NoteModel)


}