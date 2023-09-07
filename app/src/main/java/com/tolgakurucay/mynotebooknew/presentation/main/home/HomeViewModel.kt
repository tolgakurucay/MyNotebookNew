package com.tolgakurucay.mynotebooknew.presentation.main.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.use_case.auth.LogOut
import com.tolgakurucay.mynotebooknew.domain.use_case.main.AddNote
import com.tolgakurucay.mynotebooknew.domain.use_case.main.GetNote
import com.tolgakurucay.mynotebooknew.util.callService
import com.tolgakurucay.mynotebooknew.util.callServiceOneShot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val logOut: LogOut,
    private val addNote: AddNote,
    private val getNote: GetNote
) : ViewModel() {


    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state


    fun logout() {
        viewModelScope.callService(
            baseState = _state.value,
            success = {
                _state.value = HomeState(isUserLoggedOut = true)
            },
            service = {
                logOut.invoke()
            },
        )
    }

    //_state.value = HomeState(notes = context.fromJsonToList("note_item.json",NoteModel::class) ?: listOf())
    fun getNotes() {
        viewModelScope.callServiceOneShot(
            baseState = _state.value,
            success = {
                _state.value = HomeState(notes = it)
            },
            service = {
                getNote.getAllFromLocale()
            },
        )

    }

    fun addNote(model: NoteModel){
        viewModelScope.launch {
            addNote.toLocale(model).data?.let {
                if(it){
                    getNotes()
                }
            }

        }
    }


}


