package com.tolgakurucay.mynotebooknew.presentation.main.edit_or_view_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.use_case.main.UpdateNote
import com.tolgakurucay.mynotebooknew.util.callServiceOneShot
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditOrViewViewModel @Inject constructor(
    private val updateNote: UpdateNote,
) : ViewModel() {

    private val _state = mutableStateOf(EditOrViewState())
    val state: State<EditOrViewState> = _state

    fun updateNoteFromLocale(updatedModel: NoteModel) {
        viewModelScope.callServiceOneShot(
            _state.value,
            success = {
                _state.value = EditOrViewState(hasUpdated = true)
            },
            service = {
                updateNote.fromLocale(updatedModel)
            },
        )
    }

}