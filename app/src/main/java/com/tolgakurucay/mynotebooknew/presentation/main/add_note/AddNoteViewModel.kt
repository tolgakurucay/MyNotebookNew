package com.tolgakurucay.mynotebooknew.presentation.main.add_note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.use_case.main.AddNoteToLocale
import com.tolgakurucay.mynotebooknew.util.callService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(private val addNoteToLocale: AddNoteToLocale) :
    ViewModel() {

    private val _state = MutableStateFlow(AddNoteState())
    val state: StateFlow<AddNoteState> = _state


    fun saveNoteToLocalDatabase(noteModel: NoteModel) {
        viewModelScope.callService(
            baseState = _state.value,
            success = {noteId->
                _state.update {
                    it.copy(isAddedTheNote = true)
                }
            },
            service = { addNoteToLocale.invoke(noteModel) })


    }


}