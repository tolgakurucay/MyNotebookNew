package com.tolgakurucay.mynotebooknew.domain.repository

import com.tolgakurucay.mynotebooknew.domain.model.auth.CreateUserEmailPasswordRequest
import com.tolgakurucay.mynotebooknew.domain.model.auth.SignInEmailPasswordRequest
import com.tolgakurucay.mynotebooknew.domain.model.auth.SignInWEmailPassResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun logOut()
    suspend fun createUserWithEmailAndPassword(request: CreateUserEmailPasswordRequest): Flow<Boolean>
    suspend fun signInWithEmailAndPassword(requestModel: SignInEmailPasswordRequest): Flow<SignInWEmailPassResponse>
    suspend fun isUserLoggedIn(): Flow<Boolean>

}