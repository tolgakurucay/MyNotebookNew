package com.tolgakurucay.mynotebooknew.domain.use_case.main.locale

import com.tolgakurucay.mynotebooknew.domain.base.BaseUseCase
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.repository.HomeRepository
import javax.inject.Inject

class UpdateNotesFromLocale @Inject constructor(private val repository: HomeRepository) : BaseUseCase() {


    suspend operator fun invoke(list: List<NoteModel>) = execute(repository.updateNotesFromLocale(list))


}