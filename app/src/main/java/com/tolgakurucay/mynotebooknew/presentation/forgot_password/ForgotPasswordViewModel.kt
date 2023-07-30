package com.tolgakurucay.mynotebooknew.presentation.forgot_password

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.domain.base.BaseViewModel
import com.tolgakurucay.mynotebooknew.domain.model.auth.ForgotPasswordRequest
import com.tolgakurucay.mynotebooknew.domain.use_case.auth.ForgotPassword
import com.tolgakurucay.mynotebooknew.util.callService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val request: ForgotPassword
) : BaseViewModel() {

    private val _state = mutableStateOf(ForgotPasswordState())
    val state: State<ForgotPasswordState> = _state

    fun forgotPassword(email: String) {
        viewModelScope.callService(
            baseViewModel = this,
            success = {
                _state.value = ForgotPasswordState(true)
            },
            service = {
                request.invoke(
                    ForgotPasswordRequest(email)
                )
            },
        )
    }


}