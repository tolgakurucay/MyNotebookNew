package com.tolgakurucay.mynotebooknew.presentation.main.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.domain.use_case.auth.LogOut
import com.tolgakurucay.mynotebooknew.domain.use_case.main.AddNoteToLocale
import com.tolgakurucay.mynotebooknew.domain.use_case.main.GetNotesFromLocale
import com.tolgakurucay.mynotebooknew.util.callService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val logOut: LogOut,
    private val addNoteToLocale: AddNoteToLocale,
    private val getNote: GetNotesFromLocale
) : ViewModel() {


    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    init {
        getNotes()
    }


  /*  fun logout() {
        viewModelScope.callService(
            baseState = _state.value,
            success = {
                _state.value = HomeState(isUserLoggedOut = true)
            },
            service = {
                logOut.invoke()
            },
        )
    }*/

    fun getNotes() {
        Log.d("bilgitolga", "getNotes: vvv")
        viewModelScope.callService(
            baseState = _state.value,
            success = {
                Log.d("bilgitolga", "getNotes: $it")
                _state.value = HomeState(notes = it)
            },
            service = {
                getNote.invoke()
            },
        )

    }



}


