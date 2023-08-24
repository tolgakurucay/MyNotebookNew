package com.tolgakurucay.mynotebooknew.domain.use_case.main

import com.tolgakurucay.mynotebooknew.domain.repository.HomeRepository
import javax.inject.Inject

class GetNote @Inject constructor(private val homeRepository: HomeRepository) {

    suspend fun getAllFromLocale() = homeRepository.getAllNotesFromLocale()

    suspend fun getAllFromRemote() = homeRepository.getAllNotesFromRemote()

    suspend fun getAllFromLocaleDesc() = homeRepository.getAllNotesFromLocaleDesc()

    suspend fun getAllFromRemoteDesc() = homeRepository.getAllNotesFromRemoteDesc()

    suspend fun searchByTitleFromLocale(title: String) = homeRepository.searchNotesByTitleFromLocale(title)

    suspend fun searchByTitleFromRemote(title: String) = homeRepository.searchNotesByTitleFromRemote(title)

}