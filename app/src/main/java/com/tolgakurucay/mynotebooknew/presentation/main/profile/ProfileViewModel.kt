package com.tolgakurucay.mynotebooknew.presentation.main.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.domain.repository.ProfileRepository
import com.tolgakurucay.mynotebooknew.util.callService
import com.tolgakurucay.mynotebooknew.util.executeFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repo: ProfileRepository) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state.asStateFlow()


    fun getProfileInformations() {
        viewModelScope.callService(
            baseState = _state.value,
            success = {response->
                _state.update {
                    it.copy(response)
                }
            },
            service = { repo.getProfileInfo().executeFlow() })
    }

}