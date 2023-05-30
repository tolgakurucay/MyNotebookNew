package com.tolgakurucay.mynotebooknew.modules.home

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import com.tolgakurucay.mynotebooknew.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel(){

    private val _state = MutableStateFlow(HomeViewState(R.drawable.ic_launcher_background,"Tolga",23.2f,true))

    val state: StateFlow<HomeViewState>
        get() = _state

}


data class HomeViewState(
    @DrawableRes val imageResourceId : Int,
    var name : String,
    val age: Float,
    var isAdult: Boolean = false,
)