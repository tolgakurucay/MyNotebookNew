package com.tolgakurucay.mynotebooknew.presentation.activity

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.domain.base.BaseViewModel
import com.tolgakurucay.mynotebooknew.domain.use_case.auth.IsUserLoggedIn
import com.tolgakurucay.mynotebooknew.util.callService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val isUserLoggedIn: IsUserLoggedIn) :
    BaseViewModel() {

    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state


    fun isUserLoggedIn() {

        viewModelScope.callService(
            this,
            success = {
                _state.value = MainState(isUserLoggedIn = it)
            },
            service = { isUserLoggedIn.invoke() })
    }

}