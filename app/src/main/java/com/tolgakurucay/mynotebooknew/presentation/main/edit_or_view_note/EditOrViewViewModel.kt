package com.tolgakurucay.mynotebooknew.presentation.main.edit_or_view_note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteType
import com.tolgakurucay.mynotebooknew.domain.use_case.main.locale.DeleteNoteFromLocale
import com.tolgakurucay.mynotebooknew.domain.use_case.main.locale.UpdateNoteFromLocale
import com.tolgakurucay.mynotebooknew.domain.use_case.main.remote.DeleteNoteFromRemote
import com.tolgakurucay.mynotebooknew.domain.use_case.main.remote.UpdateNoteFromRemote
import com.tolgakurucay.mynotebooknew.util.callService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class EditOrViewViewModel @Inject constructor(
    private val updateNoteFromLocale: UpdateNoteFromLocale,
    private val updateNoteFromRemote: UpdateNoteFromRemote,
    private val deleteNoteFromLocale: DeleteNoteFromLocale,
    private val deleteNoteFromRemote: DeleteNoteFromRemote
) : ViewModel() {

    private val _state = MutableStateFlow(EditOrViewState())
    val state: StateFlow<EditOrViewState> = _state

    fun updateNote(updatedModel: NoteModel) {
        viewModelScope.callService(
            baseState = _state.value,
            success = {
                _state.value = _state.value.copy(hasUpdated = true)
            },
            service = {
                when (updatedModel.noteType) {
                    NoteType.NOTE.name -> updateNoteFromLocale.invoke(updatedModel)
                    NoteType.CLOUD.name -> updateNoteFromRemote.invoke(updatedModel)
                    else -> updateNoteFromLocale.invoke(updatedModel)
                }
            },
        )
    }


    fun deleteNote(note: NoteModel){
        viewModelScope.callService(baseState = _state.value,
            success = {
                _state.update { it.copy(hasDeleted = true) }
            },
            service = {
                when(note.noteType){
                    NoteType.NOTE.name -> deleteNoteFromLocale.invoke(note)
                    NoteType.FAVORITE.name -> deleteNoteFromLocale.invoke(note)
                    NoteType.CLOUD.name -> deleteNoteFromRemote.invoke(note)
                    else ->  deleteNoteFromLocale.invoke(note)
                }
            })
    }





}