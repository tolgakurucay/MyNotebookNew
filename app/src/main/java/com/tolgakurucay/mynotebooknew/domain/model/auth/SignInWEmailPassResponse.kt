package com.tolgakurucay.mynotebooknew.domain.model.auth

import com.google.firebase.auth.AuthResult
import com.tolgakurucay.mynotebooknew.domain.base.BaseException


data class SignInWEmailPassResponse(
    val authResult: AuthResult? = null,
    val baseException: BaseException? = null
)
