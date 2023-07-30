package com.tolgakurucay.mynotebooknew.presentation.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.domain.base.BaseViewModel
import com.tolgakurucay.mynotebooknew.domain.model.auth.CreateUserEmailPasswordRequest
import com.tolgakurucay.mynotebooknew.domain.use_case.auth.CreateUser
import com.tolgakurucay.mynotebooknew.domain.use_case.auth.IsUserLoggedIn
import com.tolgakurucay.mynotebooknew.util.callService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val createUserUseCase: CreateUser,
    private val isUserLoggedIn: IsUserLoggedIn
) : BaseViewModel() {

    private val _state = mutableStateOf(RegisterState())
    val state: State<RegisterState> = _state


    fun registerUser(
        name: String,
        surname: String,
        email: String,
        password: String,
        phoneNumber: String = ""
    ) {
        viewModelScope.callService(this,
            success = {
                _state.value = RegisterState(isUserRegistered = true)
            },
            service = {
                createUserUseCase.invoke(
                    CreateUserEmailPasswordRequest(
                        mail = email,
                        password = password,
                        name = name,
                        surname = surname,
                        phoneNumber = phoneNumber
                    )
                )
            }
        )

    }


    fun isUserAuthenticated() {
        viewModelScope.callService(this,
            success = {
                _state.value.isUserRegistered = true
            },
            service = {
                isUserLoggedIn.invoke()
            }
        )
    }


}