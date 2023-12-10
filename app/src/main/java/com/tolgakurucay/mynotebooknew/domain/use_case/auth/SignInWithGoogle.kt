package com.tolgakurucay.mynotebooknew.domain.use_case.auth

import com.google.firebase.auth.AuthCredential
import com.tolgakurucay.mynotebooknew.domain.base.BaseUseCase
import com.tolgakurucay.mynotebooknew.domain.repository.AuthRepository
import javax.inject.Inject

class SignInWithGoogle @Inject constructor(private val repo: AuthRepository) : BaseUseCase() {

    suspend operator fun invoke(credential: AuthCredential) = executeFlow(repo.googleSignIn(credential))
}