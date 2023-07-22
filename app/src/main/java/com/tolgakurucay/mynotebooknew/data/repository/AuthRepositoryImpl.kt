package com.tolgakurucay.mynotebooknew.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.tolgakurucay.mynotebooknew.domain.model.auth.CreateUserEmailPasswordRequest
import com.tolgakurucay.mynotebooknew.domain.model.auth.CreateUserEmailPasswordResponse
import com.tolgakurucay.mynotebooknew.domain.model.auth.SignInEmailPasswordRequest
import com.tolgakurucay.mynotebooknew.domain.model.auth.SignInEmailPasswordResponse
import com.tolgakurucay.mynotebooknew.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val auth: FirebaseAuth) : AuthRepository {

    override fun isUserAuthenticatedInFirebase(): Boolean {
        return auth.currentUser?.let {
            true
        } ?: false
    }

    override suspend fun createUserWithEmailAndPassword(request: CreateUserEmailPasswordRequest) {
        auth.createUserWithEmailAndPassword(
            request.email,
            request.password
        ).addOnSuccessListener {
            it?.let { authResult ->
                authResult.user?.sendEmailVerification()?.addOnSuccessListener {

                }?.addOnFailureListener { exception ->
                   throw Exception()

                }
            } ?: kotlin.run {
                throw Exception()

            }
        }.addOnFailureListener { exception ->
            throw Exception()



        }

    }

    override suspend fun signInWithEmailAndPassword(requestModel: SignInEmailPasswordRequest) {
        auth.signInWithEmailAndPassword(requestModel.email, requestModel.password)
            .addOnSuccessListener {

            }
            .addOnFailureListener {

            }
    }

}