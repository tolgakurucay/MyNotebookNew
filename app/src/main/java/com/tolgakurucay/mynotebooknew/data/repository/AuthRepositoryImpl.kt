package com.tolgakurucay.mynotebooknew.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tolgakurucay.mynotebooknew.domain.base.BaseException
import com.tolgakurucay.mynotebooknew.domain.base.ExceptionType
import com.tolgakurucay.mynotebooknew.domain.base.Result
import com.tolgakurucay.mynotebooknew.domain.model.auth.CreateUserEmailPasswordRequest
import com.tolgakurucay.mynotebooknew.domain.model.auth.ForgotPasswordRequest
import com.tolgakurucay.mynotebooknew.domain.model.auth.SignInEmailPasswordRequest
import com.tolgakurucay.mynotebooknew.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {
    override fun isUserAuthenticatedInFirebase(): Flow<Result<Boolean>> = flow {
        try {
            auth.currentUser?.let {
                emit(Result.success(true))
            } ?: kotlin.run {
                emit(Result.success(false))
            }
        } catch (ex: Exception) {
            emit(Result.error(BaseException(cause = ex)))
        }

    }

    override fun logOut(): Flow<Result<Boolean>> = flow{
        try {
            auth.signOut()
            emit(Result.success(true))
        }
        catch (ex: Exception){
            emit(Result.error(BaseException(cause = ex)))
        }
    }

    override suspend fun createUserWithEmailAndPassword(request: CreateUserEmailPasswordRequest): Flow<Result<Boolean>> =
        flow {
            try {
                val response = auth.createUserWithEmailAndPassword(
                    request.mail,
                    request.password
                ).await()

                response?.let {
                    val task = firestore.collection("Users").add(
                        CreateUserEmailPasswordRequest(
                            request.mail,
                            request.password,
                            request.name,
                            request.surname,
                            request.phoneNumber
                        )
                    ).await()
                    task?.let {
                        auth.signInWithEmailAndPassword(request.mail,request.password).await()?.let {
                            it.user?.sendEmailVerification()?.await()
                            emit(Result.success(true))
                        }

                    } ?: kotlin.run {
                        emit(Result.error(BaseException(ExceptionType.CREATE_EMAIL_PASSWORD)))

                    }

                } ?: kotlin.run {
                    emit(Result.error(BaseException(ExceptionType.CREATE_EMAIL_PASSWORD)))


                }

            } catch (ex: Exception) {
                emit(Result.error(BaseException(cause = ex)))
            }

        }

    override suspend fun signInWithEmailAndPassword(requestModel: SignInEmailPasswordRequest): Flow<Result<Boolean>> =
        flow {
            try {
                val response =
                    auth.signInWithEmailAndPassword(requestModel.email, requestModel.password)
                        .await()
                response?.let {
                    it.user?.let {
                        if(it.isEmailVerified){
                            emit(Result.success(true))
                        }
                        else{
                            emit(Result.error(BaseException(exceptionType = ExceptionType.EMAIL_NOT_VERIFIED)))
                        }
                    } ?: kotlin.run {
                        emit(Result.error(BaseException(exceptionType = ExceptionType.SIGNIN)))
                    }
                } ?: kotlin.run {
                    emit(Result.error(BaseException(exceptionType = ExceptionType.SIGNIN)))
                }

            } catch (ex: Exception) {
                emit(Result.error(BaseException(cause = ex)))

            }

        }

    override suspend fun forgotPassword(requestModel: ForgotPasswordRequest): Flow<Result<Boolean>> {
        return flow {
            try {
                auth.sendPasswordResetEmail(requestModel.email).await()
                emit(Result.success(true))
            } catch (ex: Exception) {
                emit(Result.error(BaseException(cause = ex)))
            }

        }
    }

    override suspend fun sendEmailVerificationLink(email: String): Flow<Result<Boolean>> = flow{
        try {
            auth.currentUser?.sendEmailVerification()?.await()
            emit(Result.success(true))
        }
        catch (ex: Exception){
            emit(Result.error(BaseException(cause = ex)))
        }
    }

    override suspend fun isUserVerifiedEmail(email: String): Flow<Result<Boolean>> = flow{
        try {
            if(auth.currentUser?.isEmailVerified == true) emit(Result.success(true)) else emit(
                Result.success(false))
        }
        catch (ex: Exception){
            emit(Result.error(BaseException(cause = ex)))
        }
    }


}





