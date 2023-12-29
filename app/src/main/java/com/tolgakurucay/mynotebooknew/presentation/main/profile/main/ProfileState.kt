package com.tolgakurucay.mynotebooknew.presentation.main.profile.main

import com.tolgakurucay.mynotebooknew.domain.base.BaseState
import com.tolgakurucay.mynotebooknew.domain.model.profile.ProfileResponse

data class ProfileState(
    val profileResponse: ProfileResponse = ProfileResponse(),
    val rights: Int? = null
) : BaseState()