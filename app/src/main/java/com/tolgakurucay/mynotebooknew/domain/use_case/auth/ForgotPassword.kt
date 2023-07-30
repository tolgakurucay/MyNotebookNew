package com.tolgakurucay.mynotebooknew.domain.use_case.auth

import com.tolgakurucay.mynotebooknew.domain.model.auth.ForgotPasswordRequest
import com.tolgakurucay.mynotebooknew.domain.repository.AuthRepository
import javax.inject.Inject

class ForgotPassword @Inject constructor(private val repository: AuthRepository){
    suspend operator fun invoke(requestModel: ForgotPasswordRequest) = repository.forgotPassword(requestModel)
}
