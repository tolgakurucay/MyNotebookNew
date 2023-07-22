package com.tolgakurucay.mynotebooknew.domain.model.auth

data class CreateUserEmailPasswordRequest(
    val email: String,
    val password: String,
    val name: String,
    val surname: String,
    val phoneNumber: String
)
