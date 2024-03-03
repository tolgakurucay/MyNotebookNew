package com.tolgakurucay.mynotebooknew.presentation.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.data.database.DataStoreManager
import com.tolgakurucay.mynotebooknew.domain.use_case.auth.IsUserLoggedIn
import com.tolgakurucay.mynotebooknew.util.callService
import com.tolgakurucay.mynotebooknew.util.executeFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val isUserLoggedIn: IsUserLoggedIn,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state

    init {
        isUserLoggedIn()
        getIsDarkModeTag()
    }

    private fun isUserLoggedIn() {
        viewModelScope.callService(
            baseState = _state.value,
            success = {
                _state.update { state -> state.copy(isUserLoggedIn = it) }
            },
            service = { isUserLoggedIn.invoke() })
    }

    private fun getIsDarkModeTag() {
        viewModelScope.launch {
            viewModelScope.callService(
                baseState = _state.value,
                success = { isDarkMode ->
                    _state.update { state -> state.copy(isDarkMode = isDarkMode) }
                },
                service = { dataStoreManager.getIsDarkModeTag().executeFlow() })
        }
    }

}