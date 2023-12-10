package com.tolgakurucay.mynotebooknew.presentation.auth.login

import com.google.firebase.auth.AuthResult
import com.tolgakurucay.mynotebooknew.domain.base.BaseState

data class LoginState(
    var isUserAuthenticated: Boolean? = null,
    var sendEmailVerificationLink: Boolean? = false,
    var googleAuthResult: AuthResult?=null
) : BaseState()
