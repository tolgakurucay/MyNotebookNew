package com.tolgakurucay.mynotebooknew.services.register


data class RegisterResponse(
    val email: String,
    val expiresIn: String,
    val idToken: String,
    val kind: String,
    val localId: String,
    val refreshToken: String
)