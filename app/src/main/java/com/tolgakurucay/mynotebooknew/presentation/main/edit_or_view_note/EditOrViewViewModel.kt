package com.tolgakurucay.mynotebooknew.presentation.main.edit_or_view_note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.use_case.main.locale.UpdateNoteFromRemote
import com.tolgakurucay.mynotebooknew.util.callService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class EditOrViewViewModel @Inject constructor(
    private val updateNoteFromRemote: UpdateNoteFromRemote
) : ViewModel() {

    private val _state = MutableStateFlow(EditOrViewState())
    val state: StateFlow<EditOrViewState> = _state

    fun updateNoteFromLocale(updatedModel: NoteModel) {
        viewModelScope.callService(
            baseState = _state.value,
            success = {
                _state.value = _state.value.copy(hasUpdated = true)
            },
            service = { updateNoteFromRemote.invoke(updatedModel) },
        )
    }



}