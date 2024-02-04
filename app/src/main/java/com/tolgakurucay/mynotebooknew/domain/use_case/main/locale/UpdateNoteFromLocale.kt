package com.tolgakurucay.mynotebooknew.domain.use_case.main.locale

import com.tolgakurucay.mynotebooknew.domain.base.BaseUseCase
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.repository.HomeRepository
import javax.inject.Inject

class UpdateNoteFromLocale @Inject constructor(private val homeRepository: HomeRepository) : BaseUseCase() {

    suspend operator fun invoke(model: NoteModel) = execute(homeRepository.updateNoteFromLocale(model))
}