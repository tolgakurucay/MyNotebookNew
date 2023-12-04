package com.tolgakurucay.mynotebooknew.domain.use_case.main

import com.tolgakurucay.mynotebooknew.domain.base.BaseUseCase
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.repository.HomeRepository
import javax.inject.Inject

class DeleteNote @Inject constructor(private val repository: HomeRepository) : BaseUseCase() {

    suspend operator fun invoke(note: NoteModel) = execute(repository.deleteNoteFromLocale(note))
}