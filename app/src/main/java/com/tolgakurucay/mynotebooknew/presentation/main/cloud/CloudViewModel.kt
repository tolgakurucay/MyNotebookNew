package com.tolgakurucay.mynotebooknew.presentation.main.cloud

import androidx.lifecycle.ViewModel
import com.tolgakurucay.mynotebooknew.domain.use_case.main.locale.GetNotesFromLocale
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CloudViewModel @Inject constructor(private val getNotes: GetNotesFromLocale): ViewModel() {

    private val _state = MutableStateFlow(CloudState())
    val state : StateFlow<CloudState> = _state


    fun getNotes(){

    }

    fun deleteNote(){

    }

    fun updateNote(){

    }



}