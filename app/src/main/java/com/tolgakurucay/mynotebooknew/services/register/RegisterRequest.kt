package com.tolgakurucay.mynotebooknew.services.register

data class RegisterRequest(
    val email: String,
    val password: String,
    val returnSecureToken: Boolean
)
