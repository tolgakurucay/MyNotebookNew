package com.tolgakurucay.mynotebooknew.domain.use_case.main

import com.tolgakurucay.mynotebooknew.domain.base.BaseUseCase
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.repository.HomeRepository
import javax.inject.Inject

class DeleteNotes @Inject constructor(private val repo: HomeRepository) : BaseUseCase() {

    suspend operator fun invoke(list: List<NoteModel>) = execute(repo.deleteNotesFromLocale(list))

}