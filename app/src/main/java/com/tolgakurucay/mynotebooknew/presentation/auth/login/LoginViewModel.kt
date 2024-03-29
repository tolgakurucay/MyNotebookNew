package com.tolgakurucay.mynotebooknew.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.domain.model.auth.SignInEmailPasswordRequest
import com.tolgakurucay.mynotebooknew.domain.use_case.auth.SignInWithEmailAndPassword
import com.tolgakurucay.mynotebooknew.util.callService
import com.tolgakurucay.mynotebooknew.util.isNotNull
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInEmailPassword: SignInWithEmailAndPassword,
) : ViewModel() {


    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun signInWithEmailAndPassword(email: String, password: String) {
        viewModelScope.callService(
            baseState = _state.value,
            success = { response ->
                if (response.authResult?.user.isNotNull()) {
                    _state.update {
                        it.copy(isUserAuthenticated = true)
                    }
                }
            },
            service = {
                signInEmailPassword.invoke(SignInEmailPasswordRequest(email, password))
            }
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


