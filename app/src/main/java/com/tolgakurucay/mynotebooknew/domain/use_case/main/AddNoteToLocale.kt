package com.tolgakurucay.mynotebooknew.domain.use_case.main

import com.tolgakurucay.mynotebooknew.domain.base.BaseUseCase
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.repository.HomeRepository
import javax.inject.Inject

class AddNoteToLocale @Inject constructor(private val homeRepository: HomeRepository): BaseUseCase() {

    suspend operator fun invoke(model: NoteModel) = execute(homeRepository.addNoteToLocale(model))

}