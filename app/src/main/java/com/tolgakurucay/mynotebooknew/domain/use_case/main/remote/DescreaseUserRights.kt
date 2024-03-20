package com.tolgakurucay.mynotebooknew.domain.use_case.main.remote

import com.tolgakurucay.mynotebooknew.domain.base.BaseUseCase
import com.tolgakurucay.mynotebooknew.domain.repository.HomeRepository
import javax.inject.Inject

class DecreaseUserRights @Inject constructor(private var homeRepository: HomeRepository): BaseUseCase() {
    suspend operator fun invoke(newRight: Int) = executeFlow(homeRepository.decreaseUserRights(newRight))
}