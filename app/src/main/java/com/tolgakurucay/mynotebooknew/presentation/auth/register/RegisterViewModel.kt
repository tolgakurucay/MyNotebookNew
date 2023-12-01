package com.tolgakurucay.mynotebooknew.presentation.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.domain.model.auth.CreateUserEmailPasswordRequest
import com.tolgakurucay.mynotebooknew.domain.use_case.auth.CreateUser
import com.tolgakurucay.mynotebooknew.util.callService
import com.tolgakurucay.mynotebooknew.util.isNotNull
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val createUserUseCase: CreateUser,
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state.asStateFlow()


    fun registerUser(
        request: CreateUserEmailPasswordRequest
    ) {
        viewModelScope.callService(
            _state.value,
            success = { authResult ->
                if (authResult.user.isNotNull()) {
                    _state.update {
                        it.copy(isUserRegistered = true)
                    }
                }
            },
            service = {
                createUserUseCase.invoke(request)
            },
            /*  fail = {baseException->
                  _state.update {
                      it.copy(isUserRegistered = false).apply { myNotebookException = baseException }
                  }
              }*/
        )

    }


}