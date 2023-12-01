package com.tolgakurucay.mynotebooknew.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tolgakurucay.mynotebooknew.domain.base.BaseException
import com.tolgakurucay.mynotebooknew.domain.base.ExceptionType
import com.tolgakurucay.mynotebooknew.domain.model.auth.CreateUserEmailPasswordRequest
import com.tolgakurucay.mynotebooknew.domain.model.auth.SignInEmailPasswordRequest
import com.tolgakurucay.mynotebooknew.domain.model.auth.SignInWEmailPassResponse
import com.tolgakurucay.mynotebooknew.domain.repository.AuthRepository
import com.tolgakurucay.mynotebooknew.util.isNotNull
import com.tolgakurucay.mynotebooknew.util.isNull
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {

    override fun logOut() = auth.signOut()

    override suspend fun createUserWithEmailAndPassword(request: CreateUserEmailPasswordRequest): Flow<Boolean> =
        flow {
            try {
                val response =
                    auth.createUserWithEmailAndPassword(request.mail, request.password).await()
                if (response.user.isNull()) {
                    emit(false)
                } else {
                    emit(true)
                }
            } catch (ex: Exception) {
                Log.d("bilgitolga", "ERROR createUserWithEmailAndPassword: $ex")
            }


        }

    override suspend fun signInWithEmailAndPassword(requestModel: SignInEmailPasswordRequest): Flow<SignInWEmailPassResponse> =
        flow {
            val response =
                auth.signInWithEmailAndPassword(requestModel.email, requestModel.password).await()
            response?.let {
                emit(SignInWEmailPassResponse(authResult = it))
            } ?: run {
                emit(SignInWEmailPassResponse(baseException = BaseException(exceptionType = ExceptionType.SIGNIN)))
            }


        }

    override suspend fun isUserLoggedIn(): Flow<Boolean> = flow {
        if (auth.currentUser.isNotNull()) {
            emit(true)
        } else {
            emit(false)
        }
    }


}





