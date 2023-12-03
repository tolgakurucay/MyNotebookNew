package com.tolgakurucay.mynotebooknew.domain.use_case.auth

import com.tolgakurucay.mynotebooknew.domain.base.BaseUseCase
import com.tolgakurucay.mynotebooknew.domain.repository.AuthRepository
import javax.inject.Inject

class LogOut @Inject constructor(private val authRepository: AuthRepository): BaseUseCase() {
    operator fun invoke() = executeFlow(authRepository.logOut())
}