package com.tolgakurucay.mynotebooknew.presentation.auth.register

data class RegisterState(
    var isUserRegistered: Boolean = false,
    var isLoggedIn: Boolean? = null
)
