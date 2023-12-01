package com.tolgakurucay.mynotebooknew.domain.use_case.auth

import com.tolgakurucay.mynotebooknew.domain.base.BaseUseCase
import com.tolgakurucay.mynotebooknew.domain.repository.AuthRepository
import javax.inject.Inject

class ForgotPassword @Inject constructor(private val repository: AuthRepository): BaseUseCase() {
    suspend operator fun invoke(email: String) = executeFlow(repository.forgotPassword(email))

}