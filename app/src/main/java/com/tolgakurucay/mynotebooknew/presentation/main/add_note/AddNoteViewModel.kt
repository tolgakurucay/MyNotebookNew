package com.tolgakurucay.mynotebooknew.presentation.main.add_note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.use_case.main.AddNoteToLocale
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(private val addNoteToLocale: AddNoteToLocale) :
    ViewModel() {

    private val _state = MutableStateFlow(AddNoteState())
    val state: StateFlow<AddNoteState> = _state


    fun saveNoteToLocalDatabase(noteModel: NoteModel) {
        viewModelScope.launch {
            addNoteToLocale.invoke(noteModel)
        }

    }


}