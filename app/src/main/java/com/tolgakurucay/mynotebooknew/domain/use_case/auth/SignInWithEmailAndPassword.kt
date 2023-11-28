package com.tolgakurucay.mynotebooknew.domain.use_case.auth

import com.tolgakurucay.mynotebooknew.domain.base.BaseUseCase
import com.tolgakurucay.mynotebooknew.domain.base.Result
import com.tolgakurucay.mynotebooknew.domain.model.auth.SignInEmailPasswordRequest
import com.tolgakurucay.mynotebooknew.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignInWithEmailAndPassword @Inject constructor(private val repository: AuthRepository) :
    BaseUseCase() {
    suspend operator fun invoke(requestModel: SignInEmailPasswordRequest) =
        executeFlow(repository.signInWithEmailAndPassword(requestModel))

}