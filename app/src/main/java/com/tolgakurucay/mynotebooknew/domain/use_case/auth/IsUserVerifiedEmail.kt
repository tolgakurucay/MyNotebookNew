package com.tolgakurucay.mynotebooknew.domain.use_case.auth

import com.tolgakurucay.mynotebooknew.domain.repository.AuthRepository
import javax.inject.Inject

class IsUserVerifiedEmail @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String) = repository.isUserVerifiedEmail(email)
}