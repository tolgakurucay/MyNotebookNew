package com.tolgakurucay.mynotebooknew.presentation.auth.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.domain.model.auth.CreateUserEmailPasswordRequest
import com.tolgakurucay.mynotebooknew.domain.use_case.auth.CreateUser
import com.tolgakurucay.mynotebooknew.util.callService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val createUserUseCase: CreateUser,
) : ViewModel() {

    private val _state = mutableStateOf(RegisterState())
    val state: State<RegisterState> = _state


    fun registerUser(
        request: CreateUserEmailPasswordRequest
    ) {
        viewModelScope.callService(_state.value,
            success = {
                _state.value = RegisterState(isUserRegistered = true)
            },
            service = {
               createUserUseCase.invoke(request)
            }
        )

    }





}