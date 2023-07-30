package com.tolgakurucay.mynotebooknew.presentation.login

data class LoginState(
    var isUserAuthenticated: Boolean = false,
    var sendEmailVerificationLink: Boolean = false
)
