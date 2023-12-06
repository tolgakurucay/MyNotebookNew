package com.tolgakurucay.mynotebooknew.presentation.main.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteType
import com.tolgakurucay.mynotebooknew.domain.use_case.main.DeleteNote
import com.tolgakurucay.mynotebooknew.domain.use_case.main.DeleteNotes
import com.tolgakurucay.mynotebooknew.domain.use_case.main.EditNote
import com.tolgakurucay.mynotebooknew.domain.use_case.main.EditNotes
import com.tolgakurucay.mynotebooknew.domain.use_case.main.GetNotesFromLocale
import com.tolgakurucay.mynotebooknew.domain.use_case.main.SearchNotesByText
import com.tolgakurucay.mynotebooknew.util.callService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getNotes: GetNotesFromLocale,
    private val updateNote: EditNote,
    private val deleteNotes: DeleteNotes,
    private val editNotes: EditNotes,
    private val searchNotesByText: SearchNotesByText

) : ViewModel() {

    private val _state = MutableStateFlow(FavoritesState())
    val state: StateFlow<FavoritesState> = _state.asStateFlow()


    fun getFavorites() {
        viewModelScope.callService(
            baseState = _state.value,
            success = { list ->
                _state.update {
                    it.copy(list.filter { it.noteType == NoteType.FAVORITE.name })
                }
            },
            service = {
                getNotes.invoke()
            },
        )
    }

    fun doSelectableOrNot(model: NoteModel) {
        viewModelScope.callService(
            baseState = _state.value,
            success = {
            },
            service = { updateNote.invoke(model) })
    }

    fun removeFromFavorites() {
        viewModelScope.callService(
            baseState = _state.value,
            success = {

            },
            service = {
                val filteredList = _state.value.list.filter { it.isSelected }
                    .map { it.copy(noteType = NoteType.NOTE.name, isSelected = false) }
                editNotes.invoke(filteredList)
            },
        )
    }

    fun removeAllSelectedItems() {
        viewModelScope.callService(
            baseState = _state.value,
            success = {},
            service = {
                val mappedList = _state.value.list.filter { it.isSelected }
                    .map { it.copy(isSelected = it.isSelected.not()) }
                editNotes.invoke(mappedList)
            },
        )
    }

    fun deleteSelectedNotes() {
        viewModelScope.callService(
            baseState = _state.value,
            success = {

            },
            service = {
                deleteNotes.invoke(_state.value.list.filter { it.isSelected })
            },
        )
    }

    fun searchNotesByText(text: String) = viewModelScope.callService(
        _state.value,
        success = {

        },
        service = { searchNotesByText.invoke(text) })


}