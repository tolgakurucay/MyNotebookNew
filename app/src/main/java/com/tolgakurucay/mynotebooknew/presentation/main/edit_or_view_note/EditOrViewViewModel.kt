package com.tolgakurucay.mynotebooknew.presentation.main.edit_or_view_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditOrViewViewModel @Inject constructor(

) : ViewModel() {

    private val _state = mutableStateOf(EditOrViewState())
    val state: State<EditOrViewState> = _state

   /* fun updateNoteFromLocale(updatedModel: NoteModel) {
        viewModelScope.callServiceOneShot(
            _state.value,
            success = {
                _state.value = EditOrViewState(hasUpdated = true)
            },
            service = {
                updateNote.fromLocale(updatedModel)
            },
        )
    }*/

}