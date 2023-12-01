package com.tolgakurucay.mynotebooknew.presentation.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.domain.use_case.auth.IsUserLoggedIn
import com.tolgakurucay.mynotebooknew.util.callService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val isUserLoggedIn: IsUserLoggedIn) :
    ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state

    init {
        isUserLoggedIn()
    }

    private fun isUserLoggedIn() {
        viewModelScope.callService(
            baseState = _state.value,
            success = {
                _state.value = _state.value.copy(isUserLoggedIn = it)
            },
            service = { isUserLoggedIn.invoke() })

    }

}