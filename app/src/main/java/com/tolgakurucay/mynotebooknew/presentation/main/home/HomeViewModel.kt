package com.tolgakurucay.mynotebooknew.presentation.main.home

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.domain.base.BaseViewModel
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.use_case.auth.LogOut
import com.tolgakurucay.mynotebooknew.util.callService
import com.tolgakurucay.mynotebooknew.util.fromJsonToList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val logOut: LogOut,
) : BaseViewModel() {


    private val _state = mutableStateOf(HomeState())
    val state : State<HomeState> = _state



    fun logout() {
        viewModelScope.callService(
            baseViewModel = this,
            success = {
                _state.value = HomeState(isUserLoggedOut = true)
            },
            service = {
                logOut.invoke()
            },
        )

    }

    fun getNotes(context: Context){
        _state.value = HomeState(notes = context.fromJsonToList("note_item.json",NoteModel::class) ?: listOf())
    }


}


