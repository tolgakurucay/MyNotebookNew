package com.tolgakurucay.mynotebooknew.domain.use_case.main

import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.repository.HomeRepository
import javax.inject.Inject

class AddNote @Inject constructor(private val homeRepository: HomeRepository) {

    suspend fun toLocale(model: NoteModel) = homeRepository.addNoteToLocale(model)

    suspend fun toRemote(model: NoteModel) = homeRepository.addNoteToRemote(model)
}