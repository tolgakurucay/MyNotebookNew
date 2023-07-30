package com.tolgakurucay.mynotebooknew.domain.use_case.auth

import com.tolgakurucay.mynotebooknew.domain.repository.AuthRepository
import javax.inject.Inject

class SendEmailVerification @Inject constructor(
    private val authRepository: AuthRepository
){
    suspend operator fun invoke(email: String) = authRepository.sendEmailVerificationLink(email)
}