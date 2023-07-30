package com.tolgakurucay.mynotebooknew.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.domain.base.BaseViewModel
import com.tolgakurucay.mynotebooknew.domain.model.auth.SignInEmailPasswordRequest
import com.tolgakurucay.mynotebooknew.domain.use_case.auth.SendEmailVerification
import com.tolgakurucay.mynotebooknew.domain.use_case.auth.SignInWithEmailAndPassword
import com.tolgakurucay.mynotebooknew.util.callService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInEmailPassword: SignInWithEmailAndPassword,
    private val sendEmailValidation: SendEmailVerification
) : BaseViewModel() {


    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state


    fun signInWithEmailAndPassword(email: String, password: String) {
        viewModelScope.callService(
            this,
            success = {
                _state.value = LoginState(isUserAuthenticated = true)
            },
            service = {
                signInEmailPassword.invoke(
                    SignInEmailPasswordRequest(email = email, password = password)
                )
            }
        )
    }

    private fun sendEmailValidationLink(email: String) {
        viewModelScope.callService(
            this,
            success = {

            },
            service = { sendEmailValidation.invoke(email = email) },
        )
    }

}


