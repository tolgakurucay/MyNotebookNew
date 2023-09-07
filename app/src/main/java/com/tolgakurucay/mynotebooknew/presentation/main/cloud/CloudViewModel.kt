package com.tolgakurucay.mynotebooknew.presentation.main.cloud

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CloudViewModel @Inject constructor(): ViewModel() {

    private val _state = mutableStateOf(CloudState())
    val state : State<CloudState> = _state



}