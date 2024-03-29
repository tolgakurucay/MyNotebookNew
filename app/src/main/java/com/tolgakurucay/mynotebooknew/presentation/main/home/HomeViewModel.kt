package com.tolgakurucay.mynotebooknew.presentation.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteType
import com.tolgakurucay.mynotebooknew.domain.use_case.auth.LogOut
import com.tolgakurucay.mynotebooknew.domain.use_case.main.DeleteNotes
import com.tolgakurucay.mynotebooknew.domain.use_case.main.EditNote
import com.tolgakurucay.mynotebooknew.domain.use_case.main.EditNotes
import com.tolgakurucay.mynotebooknew.domain.use_case.main.GetNotesFromLocale
import com.tolgakurucay.mynotebooknew.util.callService
import com.tolgakurucay.mynotebooknew.util.isNotNull
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val logOut: LogOut,
    private val getNote: GetNotesFromLocale,
    private val updateNote: EditNote,
    private val deleteNotes: DeleteNotes,
    private val editNotes: EditNotes
) : ViewModel() {


    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    fun getNotes() {
        viewModelScope.callService(
            baseState = _state.value,
            success = { list ->
                val isAnySelected = list.find { it.isSelected }
                _state.update {
                    it.copy(notes = list, isShowingTheMenu = isAnySelected.isNotNull())
                }
            },
            service = {
                getNote.invoke()
            },
        )
    }

    fun logOut() {
        viewModelScope.callService(
            baseState = _state.value,
            success = {
                _state.update {
                    it.copy(isUserLoggedOut = true)
                }
            },
            service = { logOut.invoke() })
    }


    fun doSelectableOrNot(model: NoteModel) {
        viewModelScope.callService(
            baseState = _state.value,
            success = {
            },
            service = { updateNote.invoke(model) })
    }



    fun addNotesToFavorite(list: List<NoteModel>) {
        val mappedList = list.map { it.copy(noteType = NoteType.FAVORITE.name, isSelected = false) }
        viewModelScope.callService(
            baseState = _state.value,
            success = {

            },
            service = {
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
                deleteNotes.invoke(_state.value.notes.filter { it.isSelected })
            },
        )
    }

    fun removeAllSelectedItems() {
        viewModelScope.callService(
            baseState = _state.value,
            success = {},
            service = {
                val mappedList = _state.value.notes.filter { it.isSelected }
                    .map { it.copy(isSelected = it.isSelected.not()) }
                editNotes.invoke(mappedList)
            },
        )
    }


}


