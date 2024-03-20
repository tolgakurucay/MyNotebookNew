package com.tolgakurucay.mynotebooknew.presentation.main.cloud

import com.tolgakurucay.mynotebooknew.domain.base.BaseState
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel

data class CloudState(
    val noteList: ArrayList<NoteModel> = arrayListOf(),
    val noteDeleted: Boolean = false,
    val noteUpdated: Boolean = false,
    val allNotesDeleted: Boolean = false,
    val isShowingTheMenu: Boolean = false,
    val changeStateManually: Boolean = false,
    val filteredNoteList: List<NoteModel> = listOf()
    ) : BaseState()
