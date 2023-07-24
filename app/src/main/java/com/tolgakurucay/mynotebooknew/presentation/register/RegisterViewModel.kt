package com.tolgakurucay.mynotebooknew.presentation.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.domain.base.BaseViewModel
import com.tolgakurucay.mynotebooknew.domain.model.auth.CreateUserEmailPasswordRequest
import com.tolgakurucay.mynotebooknew.domain.use_case.auth.CreateUser
import com.tolgakurucay.mynotebooknew.domain.use_case.auth.IsUserLoggedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val createUserUseCase: CreateUser,
    private val isUserLoggedIn: IsUserLoggedIn
) : ViewModel() {

    private val _state = mutableStateOf(RegisterState())
    val state: State<RegisterState> = _state

    private var job: Job? = null


    fun registerUser(
        name: String,
        surname: String,
        email: String,
        password: String,
        phoneNumber: String = ""
    ) {
        viewModelScope.launch {
          val data =  createUserUseCase.invoke(
                CreateUserEmailPasswordRequest(
                    email = email,
                    password = password,
                    name = name,
                    surname = surname,
                    phoneNumber = phoneNumber
                )
            )


        }


    }


    fun isUserAuthenticated() {
        viewModelScope.launch {
            isUserLoggedIn.invoke()
        }
    }


}