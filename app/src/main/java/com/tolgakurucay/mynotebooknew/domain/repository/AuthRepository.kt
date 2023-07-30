package com.tolgakurucay.mynotebooknew.domain.repository

import com.tolgakurucay.mynotebooknew.domain.model.auth.CreateUserEmailPasswordRequest
import com.tolgakurucay.mynotebooknew.domain.model.auth.SignInEmailPasswordRequest
import com.tolgakurucay.mynotebooknew.domain.model.Result
import com.tolgakurucay.mynotebooknew.domain.model.auth.ForgotPasswordRequest
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun isUserAuthenticatedInFirebase(): Flow<Result<Boolean>>
    suspend fun createUserWithEmailAndPassword(request: CreateUserEmailPasswordRequest): Flow<Result<Boolean>>
    suspend fun signInWithEmailAndPassword(requestModel: SignInEmailPasswordRequest): Flow<Result<Boolean>>
    suspend fun forgotPassword(requestModel: ForgotPasswordRequest): Flow<Result<Boolean>>
    suspend fun sendEmailVerificationLink(email: String): Flow<Result<Boolean>>
    suspend fun isUserVerifiedEmail(email: String): Flow<Result<Boolean>>


}