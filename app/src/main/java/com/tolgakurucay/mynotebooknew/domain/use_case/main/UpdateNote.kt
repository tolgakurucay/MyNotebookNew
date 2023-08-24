package com.tolgakurucay.mynotebooknew.domain.use_case.main

import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.repository.HomeRepository
import javax.inject.Inject

class UpdateNote @Inject constructor(private val repo: HomeRepository) {

    suspend fun fromLocale(model: NoteModel) = repo.updateNoteFromLocale(model)

    suspend fun fromRemote(model: NoteModel) = repo.updateNoteFromRemote(model)

}