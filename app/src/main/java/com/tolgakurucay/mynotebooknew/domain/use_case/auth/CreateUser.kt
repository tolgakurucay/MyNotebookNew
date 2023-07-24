package com.tolgakurucay.mynotebooknew.domain.use_case.auth

import com.tolgakurucay.mynotebooknew.domain.model.auth.CreateUserEmailPasswordRequest
import com.tolgakurucay.mynotebooknew.domain.repository.AuthRepository
import com.tolgakurucay.mynotebooknew.util.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CreateUser @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(request: CreateUserEmailPasswordRequest) = repository.createUserWithEmailAndPassword(request)

}