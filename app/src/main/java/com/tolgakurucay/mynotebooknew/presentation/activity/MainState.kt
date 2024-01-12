package com.tolgakurucay.mynotebooknew.presentation.activity

import com.tolgakurucay.mynotebooknew.domain.base.BaseState

data class MainState(
    var isUserLoggedIn: Boolean = false,
    var isDarkMode: Boolean? = null,
) : BaseState()
