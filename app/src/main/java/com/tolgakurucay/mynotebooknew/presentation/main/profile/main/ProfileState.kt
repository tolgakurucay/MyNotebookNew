package com.tolgakurucay.mynotebooknew.presentation.main.profile.main

import com.tolgakurucay.mynotebooknew.domain.base.BaseState
import com.tolgakurucay.mynotebooknew.domain.model.profile.ProfileResponse
import com.tolgakurucay.mynotebooknew.presentation.main.profile.light_dark_mode.ViewMode

data class ProfileState(
    val profileResponse: ProfileResponse = ProfileResponse(),
    val rights: Int? = null,
    val viewMode: ViewMode? = null,
) : BaseState()