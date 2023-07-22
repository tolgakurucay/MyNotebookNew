package com.tolgakurucay.mynotebooknew.domain.repository

import com.tolgakurucay.mynotebooknew.domain.model.auth.CreateUserEmailPasswordRequest
import com.tolgakurucay.mynotebooknew.domain.model.auth.CreateUserEmailPasswordResponse
import com.tolgakurucay.mynotebooknew.domain.model.auth.SignInEmailPasswordRequest
import com.tolgakurucay.mynotebooknew.domain.model.auth.SignInEmailPasswordResponse

interface AuthRepository {

    fun isUserAuthenticatedInFirebase(): Boolean
    suspend fun createUserWithEmailAndPassword(request: CreateUserEmailPasswordRequest)
    suspend fun signInWithEmailAndPassword(requestModel: SignInEmailPasswordRequest)

}