package com.tolgakurucay.mynotebooknew.domain.use_case.main

import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.repository.HomeRepository
import javax.inject.Inject

class DeleteNote @Inject constructor(private val homeRepository: HomeRepository){

    suspend fun deleteFromLocale(model: NoteModel) = homeRepository.deleteNoteFromLocale(model)

    suspend fun deleteFromRemote(model: NoteModel) = homeRepository.deleteNoteFromRemote(model)

    suspend fun deleteAllFromLocale() = homeRepository.deleteAllNotesFromLocale()

    suspend fun deleteAllFromRemote() = homeRepository.deleteAllNotesFromRemote()

}