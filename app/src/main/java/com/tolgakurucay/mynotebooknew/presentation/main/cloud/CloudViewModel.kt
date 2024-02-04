package com.tolgakurucay.mynotebooknew.presentation.main.cloud

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteType
import com.tolgakurucay.mynotebooknew.domain.use_case.main.locale.GetNotesFromLocale
import com.tolgakurucay.mynotebooknew.domain.use_case.main.remote.DeleteNoteFromRemote
import com.tolgakurucay.mynotebooknew.domain.use_case.main.remote.DeleteNotesFromRemote
import com.tolgakurucay.mynotebooknew.domain.use_case.main.remote.GetNotesFromRemote
import com.tolgakurucay.mynotebooknew.domain.use_case.main.remote.UpdateNoteFromRemote
import com.tolgakurucay.mynotebooknew.util.callService
import com.tolgakurucay.mynotebooknew.util.isNotNull
import com.tolgakurucay.mynotebooknew.util.toArrayList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CloudViewModel @Inject constructor(
    private val getNotes: GetNotesFromRemote,
    private val deleteNote: DeleteNoteFromRemote,
    private val updateNote: UpdateNoteFromRemote,
    private val deleteNotes: DeleteNotesFromRemote
) : ViewModel() {

    private val _state = MutableStateFlow(CloudState())
    val state: StateFlow<CloudState> = _state

    init {
        getNotes()
    }

    fun getNotes() {
        viewModelScope.callService(
            baseState = _state.value,
            success = { list ->
                _state.update {
                    val isAnySelected = list.find { it.isSelected }
                    it.copy(
                        list.filter { it.noteType == NoteType.CLOUD.name }.toArrayList(),
                        isShowingTheMenu = isAnySelected.isNotNull()
                    )
                }
            },
            service = { getNotes.invoke() }
        )
    }

    fun deleteNote(noteModel: NoteModel) {
        viewModelScope.callService(
            baseState = _state.value,
            success = { wasDeleted ->
                _state.update {
                    it.copy(noteDeleted = wasDeleted)
                }
            },
            service = { deleteNote.invoke(noteModel) }
        )
    }

    fun updateNote(noteModel: NoteModel) {
        viewModelScope.callService(
            baseState = _state.value,
            success = { wasUpdated ->
                _state.update {
                    it.copy(noteUpdated = wasUpdated)
                }
            },
            service = { updateNote.invoke(noteModel) }
        )
    }

    fun deleteNotes(noteList: List<NoteModel>) {
        viewModelScope.callService(
            baseState = _state.value,
            success = { wasAllDeleted ->
                _state.update {
                    it.copy(allNotesDeleted = wasAllDeleted)
                }
            },
            service = { deleteNotes.invoke(noteList) }
        )
    }

    fun doSelectableOrNot(model: NoteModel){
        val noteList = _state.value.noteList
        val modelIndex = noteList.indexOf(model)
        model.isSelected = model.isSelected.not()
        noteList[modelIndex] = model
        _state.update {
            it.copy(noteList = noteList)
        }
    }

    fun deleteSelectedNotes() {
        viewModelScope.callService(
            baseState = _state.value,
            success = { wasAllDeleted ->
                if (wasAllDeleted) getNotes()
            },
            service = { deleteNotes.invoke(_state.value.noteList.filter { it.isSelected }) }
        )
    }

    // TODO:
    fun searchNotesByText(searchString: String){

    }




}