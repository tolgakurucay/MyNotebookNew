package com.tolgakurucay.mynotebooknew.domain.use_case.main.remote

import com.tolgakurucay.mynotebooknew.domain.base.BaseUseCase
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.repository.HomeRepository
import javax.inject.Inject

class AddNotesToRemote @Inject constructor(private val repository: HomeRepository): BaseUseCase() {

    suspend operator fun invoke(list: List<NoteModel>) = executeFlow(repository.addNotesToRemote(list))

}