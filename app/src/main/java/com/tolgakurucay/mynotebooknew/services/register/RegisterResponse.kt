package com.tolgakurucay.mynotebooknew.services.register

import com.tolgakurucay.mynotebooknew.base.BaseApiResponse


data class RegisterResponse(
    val email: String,
    val expiresIn: String,
    val idToken: String,
    val kind: String,
    val localId: String,
    val refreshToken: String
) : BaseApiResponse()