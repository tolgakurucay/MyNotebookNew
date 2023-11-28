package com.tolgakurucay.mynotebooknew.presentation.auth.forgot_password

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.domain.model.auth.ForgotPasswordRequest
import com.tolgakurucay.mynotebooknew.util.callService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
) : ViewModel() {

    private val _state = mutableStateOf(ForgotPasswordState())
    val state: State<ForgotPasswordState> = _state

    /*fun forgotPassword(email: String) {
        viewModelScope.callService(
            baseState = _state.value,
            success = {
                _state.value = ForgotPasswordState(true)
            },
            service = {
                request.invoke(
                    ForgotPasswordRequest(email)
                )
            },
        )
    }*/


}