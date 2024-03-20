package com.tolgakurucay.mynotebooknew.domain.use_case.main.locale

import com.tolgakurucay.mynotebooknew.domain.base.BaseUseCase
import com.tolgakurucay.mynotebooknew.domain.repository.HomeRepository
import javax.inject.Inject

class SearchNotesByText @Inject constructor(private val repo:HomeRepository) : BaseUseCase() {

    suspend operator fun invoke(searchNotesByText: String) = executeFlow(repo.searchNoteByAll(searchNotesByText))
}