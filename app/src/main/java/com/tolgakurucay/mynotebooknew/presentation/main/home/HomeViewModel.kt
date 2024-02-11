package com.tolgakurucay.mynotebooknew.presentation.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteType
import com.tolgakurucay.mynotebooknew.domain.repository.AlarmScheduler
import com.tolgakurucay.mynotebooknew.domain.use_case.auth.LogOut
import com.tolgakurucay.mynotebooknew.domain.use_case.main.locale.DeleteNotesFromLocale
import com.tolgakurucay.mynotebooknew.domain.use_case.main.locale.UpdateNoteFromLocale
import com.tolgakurucay.mynotebooknew.domain.use_case.main.locale.UpdateNotesFromLocale
import com.tolgakurucay.mynotebooknew.domain.use_case.main.locale.GetNotesFromLocale
import com.tolgakurucay.mynotebooknew.domain.use_case.main.locale.SearchNotesByText
import com.tolgakurucay.mynotebooknew.domain.use_case.main.remote.AddNoteToRemote
import com.tolgakurucay.mynotebooknew.domain.use_case.main.remote.AddNotesToRemote
import com.tolgakurucay.mynotebooknew.domain.use_case.main.remote.DecreaseUserRights
import com.tolgakurucay.mynotebooknew.domain.use_case.main.remote.GetUserRights
import com.tolgakurucay.mynotebooknew.util.callService
import com.tolgakurucay.mynotebooknew.util.isNotNull
import com.tolgakurucay.mynotebooknew.util.safeLet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val logOut: LogOut,
    private val getNote: GetNotesFromLocale,
    private val updateNote: UpdateNoteFromLocale,
    private val deleteNotesFromLocale: DeleteNotesFromLocale,
    private val updateNotesFromLocale: UpdateNotesFromLocale,
    private val searchNotesByText: SearchNotesByText,
    private val alarmScheduler: AlarmScheduler,
    private val addNotesToRemote: AddNotesToRemote,
    private val getUserRights: GetUserRights,
    private val decreaseUserRights: DecreaseUserRights
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
                updateNotesFromLocale.invoke(mappedList)
            },
        )
    }

    fun deleteSelectedNotes() {
        viewModelScope.callService(
            baseState = _state.value,
            success = {

            },
            service = {
                deleteNotesFromLocale.invoke(_state.value.notes.filter { it.isSelected })
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
                updateNotesFromLocale.invoke(mappedList)
            },
        )
    }

    fun searchNotesByText(text: String) = viewModelScope.callService(
        _state.value,
        success = { list ->
            _state.update {
                it.copy(notes = list.filter { it.noteType == NoteType.NOTE.name })
            }
        },
        service = { searchNotesByText.invoke(text) },
    )

    fun setAnAlarm(model: NoteModel) {
        alarmScheduler.schedule(model)
        viewModelScope.callService(baseState = _state.value, success = {
            _state.update {
                it.copy(isSnackBarShow = true)
            }
        }, service = { updateNote.invoke(model) })

    }

    fun cancelTheAlarm(model: NoteModel) {
        alarmScheduler.cancel(model)
        viewModelScope.callService(baseState = _state.value, success = {
            _state.update {
                it.copy(isSnackBarShow = false)
            }
        }, service = { updateNote.invoke(model) })

    }

    fun dismissSnackBar() {
        _state.update { it.copy(isSnackBarShow = false) }
    }

    fun addSelectedNotesToCloud(noteList: List<NoteModel>) {
        viewModelScope.callService(
            baseState = _state.value,
            success = {
                safeLet(_state.value.userRights,_state.value.notes.filter { it.isSelected }){right, selectedNotes->
                    decreaseUserRights(right - selectedNotes.size)
                    getNotes()
                }
            },
            service = {
                addNotesToRemote.invoke(noteList)
            },
        )
    }

    fun getUserRights() {
        viewModelScope.callService(
            baseState = _state.value,
            success = { right ->
                _state.update { it.copy(userRights = right) }
            },
            service = {
                getUserRights.invoke()
            },
        )
    }

    private fun decreaseUserRights(newRight: Int) {
        viewModelScope.callService(
            baseState = _state.value,
            success = {
                _state.update {
                    it.copy(userRights = newRight)
                }
            },
            service = {
                decreaseUserRights.invoke(newRight)
            },
        )
    }




}


