package com.tolgakurucay.mynotebooknew.modules.register

import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.base.BaseViewModel
import com.tolgakurucay.mynotebooknew.extensions.callService
import com.tolgakurucay.mynotebooknew.services.register.RegisterRequest
import com.tolgakurucay.mynotebooknew.services.register.RegisterResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository: RegisterRepository) :
    BaseViewModel() {

    val registerResponse = MutableStateFlow<RegisterResponse?>(null)

    fun register(request: RegisterRequest) {
        viewModelScope.callService(
            vm = this,
            success = {
                registerResponse.value = it
            },
            service = {
              repository.register(request)
            }
        )
    }

}