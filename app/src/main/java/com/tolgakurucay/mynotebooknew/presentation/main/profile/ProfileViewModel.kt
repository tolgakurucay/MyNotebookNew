package com.tolgakurucay.mynotebooknew.presentation.main.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.tolgakurucay.mynotebooknew.domain.base.BaseState
import javax.inject.Inject

class ProfileViewModel @Inject constructor() : ViewModel() {

    private val _state = mutableStateOf(ProfileState())
    val state : State<ProfileState> = _state

}