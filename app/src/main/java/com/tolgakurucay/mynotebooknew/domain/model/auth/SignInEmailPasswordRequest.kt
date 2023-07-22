package com.tolgakurucay.mynotebooknew.domain.model.auth

data class SignInEmailPasswordRequest(
    val email: String,
    val password: String
)
