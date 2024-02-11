package com.tolgakurucay.mynotebooknew.domain.use_case.main.remote

import com.tolgakurucay.mynotebooknew.domain.base.BaseUseCase
import com.tolgakurucay.mynotebooknew.domain.repository.HomeRepository
import javax.inject.Inject

class GetUserRights @Inject constructor(private val homeRepository: HomeRepository): BaseUseCase() {
    suspend operator fun invoke() = executeFlow(homeRepository.getUserRights())
}