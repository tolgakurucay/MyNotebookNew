package com.tolgakurucay.mynotebooknew.presentation.auth.register

import com.tolgakurucay.mynotebooknew.domain.base.BaseState

data class RegisterState(
    var isUserRegistered: Boolean = false,
    var isLoggedIn: Boolean? = null
) : BaseState()
