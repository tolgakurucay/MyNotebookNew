package com.tolgakurucay.mynotebooknew.domain.use_case.auth

import com.tolgakurucay.mynotebooknew.domain.repository.AuthRepository
import javax.inject.Inject

class IsUserLoggedIn @Inject constructor(private val repository: AuthRepository){
    operator fun invoke() = repository.isUserAuthenticatedInFirebase()
}