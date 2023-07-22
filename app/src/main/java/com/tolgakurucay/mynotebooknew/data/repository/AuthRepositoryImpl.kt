package com.tolgakurucay.mynotebooknew.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tolgakurucay.mynotebooknew.domain.model.Result
import com.tolgakurucay.mynotebooknew.domain.model.auth.CreateUserEmailPasswordRequest
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
            emit(Result.error(ex.localizedMessage ?: ""))
        }

    }


    override suspend fun createUserWithEmailAndPassword(request: CreateUserEmailPasswordRequest): Flow<Result<Boolean>> =
        flow {
            try {
                val response = auth.createUserWithEmailAndPassword(
                    request.email,
                    request.password
                ).await()

                response?.let {
                    val task = firestore.collection("Users").add(
                        CreateUserEmailPasswordRequest(
                            request.email,
                            request.password,
                            request.name,
                            request.surname,
                            request.phoneNumber
                        )
                    ).await()
                    task?.let {
                        emit(Result.success(true))

                    } ?: kotlin.run {
                        emit(Result.error(""))

                    }

                } ?: kotlin.run {
                    emit(Result.error(""))

                }

            } catch (ex: Exception) {
                emit(Result.error(ex.localizedMessage ?: ""))
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
                        emit(Result.success(true))
                    } ?: kotlin.run {
                        emit(Result.error(""))
                    }
                } ?: kotlin.run {
                    emit(Result.error(""))

                }

            } catch (ex: Exception) {
                emit(Result.error(ex.localizedMessage ?: ""))
            }

        }


}