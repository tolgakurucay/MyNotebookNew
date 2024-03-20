package com.tolgakurucay.mynotebooknew.presentation.main.cloud

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteType
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
    private val updateNote: UpdateNoteFromRemote,
    private val deleteNotes: DeleteNotesFromRemote
) : ViewModel() {

    private var _state = MutableStateFlow(CloudState())
    val state: StateFlow<CloudState> = _state

    init {
        getNotes()
    }

    private fun getNotes() {
        viewModelScope.callService(
            baseState = _state.value,
            success = { list ->
                _state.update {
                    val isAnySelected = list.find { it.isSelected }
                    it.copy(
                        noteList = list.filter { it.noteType == NoteType.CLOUD.name }.toArrayList(),
                        filteredNoteList = list,
                        isShowingTheMenu = isAnySelected.isNotNull()
                    )
                }
            },
            service = { getNotes.invoke() }
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

    fun doSelectableOrNot(model: NoteModel) {
        val currentList = _state.value.noteList
        val modelIndex = currentList.indexOf(model)
        model.isSelected = model.isSelected.not()
        currentList[modelIndex] = model
        _state.update {
            it.copy(
                noteList = currentList.toArrayList(),
                changeStateManually = _state.value.changeStateManually.not(),
                isShowingTheMenu = currentList.any { it.isSelected })
        }
    }

    fun deleteSelectedNotes() {
        viewModelScope.callService(
            baseState = _state.value,
            success = { wasAllDeleted ->
                if (wasAllDeleted) getNotes(); _state.value
            },
            service = { deleteNotes.invoke(_state.value.noteList.filter { it.isSelected }) }
        )
    }

    fun searchNotesByText(searchString: String) {
        if (searchString.isEmpty()) {
            _state.update {
                it.copy(filteredNoteList = _state.value.noteList)
            }
        } else {
            Log.d("bilgitolga", "searchNotesByText: ${_state.value.noteList.filter {
                it.title.orEmpty().startsWith(searchString) || it.description.orEmpty()
                    .startsWith(searchString)
            }}")
            _state.update {
                it.copy(
                    filteredNoteList = _state.value.noteList.filter {
                        it.title.orEmpty().startsWith(searchString) || it.description.orEmpty()
                            .startsWith(searchString)
                    },
                )
            }
        }

    }
}