package com.tolgakurucay.mynotebooknew.presentation.auth.login

import com.tolgakurucay.mynotebooknew.domain.base.BaseState

data class LoginState(
    var isUserAuthenticated: Boolean = false,
    var sendEmailVerificationLink: Boolean = false,
) : BaseState()
