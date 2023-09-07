package com.tolgakurucay.mynotebooknew.presentation.main.favorites

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class FavoritesViewModel @Inject constructor() : ViewModel() {

    private val _state = mutableStateOf(FavoritesState())
    val state : State<FavoritesState> = _state


}