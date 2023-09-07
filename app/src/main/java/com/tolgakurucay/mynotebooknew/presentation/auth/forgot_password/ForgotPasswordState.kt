package com.tolgakurucay.mynotebooknew.presentation.auth.forgot_password

import com.tolgakurucay.mynotebooknew.domain.base.BaseState

data class ForgotPasswordState(
    var sendResetMail: Boolean = false
) : BaseState()