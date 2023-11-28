package com.tolgakurucay.mynotebooknew.presentation.activity

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.util.callService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() :
    ViewModel() {

    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state



    /*private fun isUserLoggedIn() {
        viewModelScope.callService(
            baseState = _state.value,
            success = {
                _state.value = MainState(isUserLoggedIn = it)
            },
            service = { isUserLoggedIn.invoke() })
    }*/

}