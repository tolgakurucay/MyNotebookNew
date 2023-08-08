package com.tolgakurucay.mynotebooknew.presentation.main.add_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.tolgakurucay.mynotebooknew.domain.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor() : BaseViewModel() {
    
    private val _state = mutableStateOf(AddNoteState())
    val state : State<AddNoteState> = _state


    // TODO:

    
    
    
}