package com.tolgakurucay.mynotebooknew.presentation.main.home

import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel

data class HomeState(
    var isUserLoggedOut: Boolean? = null,
    val notes: List<NoteModel> = listOf()
)