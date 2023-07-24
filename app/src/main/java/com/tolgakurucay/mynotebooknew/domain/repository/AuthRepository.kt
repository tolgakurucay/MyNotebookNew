package com.tolgakurucay.mynotebooknew.domain.repository

import com.tolgakurucay.mynotebooknew.domain.model.auth.CreateUserEmailPasswordRequest
import com.tolgakurucay.mynotebooknew.domain.model.auth.SignInEmailPasswordRequest
import com.tolgakurucay.mynotebooknew.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun isUserAuthenticatedInFirebase(): Result<Boolean>
    suspend fun createUserWithEmailAndPassword(request: CreateUserEmailPasswordRequest): Result<Boolean>
    suspend fun signInWithEmailAndPassword(requestModel: SignInEmailPasswordRequest): Result<Boolean>

}