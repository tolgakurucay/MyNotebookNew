package com.tolgakurucay.mynotebooknew.presentation.auth.login

data class LoginState(
    var isUserAuthenticated: Boolean = false,
    var sendEmailVerificationLink: Boolean = false,
)
