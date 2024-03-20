package com.tolgakurucay.mynotebooknew.domain.use_case.main.remote

import com.tolgakurucay.mynotebooknew.domain.base.BaseUseCase
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.repository.HomeRepository
import javax.inject.Inject

class UpdateNoteFromRemote @Inject constructor(private val repo: HomeRepository): BaseUseCase() {

    suspend operator fun invoke(noteModel: NoteModel) = executeFlow(repo.updateNoteFromRemote(noteModel))

}