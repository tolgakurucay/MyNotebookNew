package com.tolgakurucay.mynotebooknew.presentation.main.add_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.use_case.main.AddNote
import com.tolgakurucay.mynotebooknew.util.callServiceOneShot
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(private val addNote: AddNote) : ViewModel() {

    private val _state = mutableStateOf(AddNoteState())
    val state: State<AddNoteState> = _state


    fun saveNoteToLocalDatabase(noteModel: NoteModel) {
        viewModelScope.callServiceOneShot(
            _state.value,
            success = {
                if (it) {
                    _state.value = AddNoteState(isAddedTheNote = true)
                } else {
                    _state.value = AddNoteState(isAddedTheNote = false)
                }
            },
            service = {
                addNote.toLocale(noteModel)
            },
        )
    }


}