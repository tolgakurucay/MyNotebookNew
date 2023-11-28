package com.tolgakurucay.mynotebooknew.domain.use_case.auth

import com.tolgakurucay.mynotebooknew.domain.base.BaseUseCase
import com.tolgakurucay.mynotebooknew.domain.model.auth.CreateUserEmailPasswordRequest
import com.tolgakurucay.mynotebooknew.domain.repository.AuthRepository
import javax.inject.Inject

class CreateUser @Inject constructor(private val repository: AuthRepository): BaseUseCase() {
    suspend operator fun invoke(request: CreateUserEmailPasswordRequest) = executeFlow(repository.createUserWithEmailAndPassword(request))

}