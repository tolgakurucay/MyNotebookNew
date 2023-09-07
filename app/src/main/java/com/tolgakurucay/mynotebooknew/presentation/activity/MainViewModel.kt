package com.tolgakurucay.mynotebooknew.presentation.activity

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.domain.use_case.auth.IsUserLoggedIn
import com.tolgakurucay.mynotebooknew.util.callService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val isUserLoggedIn: IsUserLoggedIn) :
    ViewModel() {

    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state


    fun isUserLoggedIn() {

        viewModelScope.callService(
            _state.value,
            success = {
                _state.value = MainState(isUserLoggedIn = it)
            },
            service = { isUserLoggedIn.invoke() })
    }

}