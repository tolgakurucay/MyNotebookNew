package com.tolgakurucay.mynotebooknew.domain.use_case.main.remote

import com.tolgakurucay.mynotebooknew.domain.base.BaseUseCase
import com.tolgakurucay.mynotebooknew.domain.base.Result
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteType
import com.tolgakurucay.mynotebooknew.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddNotesToRemote @Inject constructor(private val repository: HomeRepository): BaseUseCase() {

    suspend operator fun invoke(list: List<NoteModel>): Flow<Result<Boolean>>  {
        val filteredList = list.filter { it.isSelected && it.noteType == NoteType.NOTE.name }
        val newList = filteredList.map { it.copy(noteType = NoteType.CLOUD.name, isSelected = false) }
        return executeFlow(repository.addNotesToRemote(newList))
    }

}