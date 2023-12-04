package com.tolgakurucay.mynotebooknew.presentation.main.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.domain.use_case.main.GetNotesFromLocale
import com.tolgakurucay.mynotebooknew.util.callService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val getNotes: GetNotesFromLocale) :
    ViewModel() {

    private val _state = MutableStateFlow(FavoritesState())
    val state: StateFlow<FavoritesState> = _state.asStateFlow()


    fun getFavorites() {
        viewModelScope.callService(
            baseState = _state.value,
            success = { list ->
                _state.update {
                    it.copy(list)
                }
            },
            service = {
                getNotes.invoke()
            },
        )

    }


}