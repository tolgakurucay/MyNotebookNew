package com.tolgakurucay.mynotebooknew.presentation.auth.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.domain.base.ExceptionType
import com.tolgakurucay.mynotebooknew.domain.model.auth.SignInEmailPasswordRequest
import com.tolgakurucay.mynotebooknew.domain.use_case.auth.SendEmailVerification
import com.tolgakurucay.mynotebooknew.domain.use_case.auth.SignInWithEmailAndPassword
import com.tolgakurucay.mynotebooknew.util.callService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInEmailPassword: SignInWithEmailAndPassword,
    private val sendEmailValidation: SendEmailVerification,
) : ViewModel() {


    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state


    fun signInWithEmailAndPassword(email: String, password: String) {
        viewModelScope.callService(
            _state.value,
            success = {
                _state.value = LoginState(isUserAuthenticated = true)
            },
            service = {
                signInEmailPassword.invoke(
                    SignInEmailPasswordRequest(email = email, password = password)
                )
            },
            fail = {
                it?.let {
                    if (it.exceptionType == ExceptionType.EMAIL_NOT_VERIFIED) {
                        sendEmailValidation.invoke(email)
                    }
                    _state.value.myNotebookException = it
                }
            },
        )
    }

    // TODO:
    fun signInWithFacebook() {
    }

    // TODO:
    fun signInWithGoogle() {

    }

    // TODO:
    fun signInWithPhone() {

    }


}


