package com.tolgakurucay.mynotebooknew.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tolgakurucay.mynotebooknew.domain.model.Result
import com.tolgakurucay.mynotebooknew.domain.model.auth.CreateUserEmailPasswordRequest
import com.tolgakurucay.mynotebooknew.domain.model.auth.SignInEmailPasswordRequest
import com.tolgakurucay.mynotebooknew.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {
    val TAG = "bilgitolga"
    override fun isUserAuthenticatedInFirebase(): Result<Boolean> {
        try {
            auth.currentUser?.let {
                return Result.success(true)
            } ?: kotlin.run {
                return Result.success(false)
            }
        } catch (ex: Exception) {
            return Result.error(ex.localizedMessage ?: "")
        }

    }

    override suspend fun createUserWithEmailAndPassword(request: CreateUserEmailPasswordRequest): Result<Boolean> {
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
                    return Result.success(true)

                } ?: kotlin.run {
                    return Result.error("")

                }

            } ?: kotlin.run {
                return Result.error("")

            }

        } catch (ex: Exception) {
            return Result.error(ex.localizedMessage ?: "")
        }

    }

    override suspend fun signInWithEmailAndPassword(requestModel: SignInEmailPasswordRequest): Result<Boolean> {
        try {
            val response =
                auth.signInWithEmailAndPassword(requestModel.email, requestModel.password)
                    .await()
            response?.let {
                it.user?.let {
                    return Result.success(true)
                } ?: kotlin.run {
                    return Result.error("")
                }
            } ?: kotlin.run {
                return Result.error("")

            }

        } catch (ex: Exception) {
            return Result.error(ex.localizedMessage ?: "")
        }

    }


}





