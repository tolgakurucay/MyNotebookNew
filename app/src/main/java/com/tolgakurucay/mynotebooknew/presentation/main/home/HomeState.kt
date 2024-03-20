package com.tolgakurucay.mynotebooknew.presentation.main.home

import com.tolgakurucay.mynotebooknew.domain.base.BaseState
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel

data class HomeState(
    var isUserLoggedOut: Boolean? = null,
    val notes: List<NoteModel> = listOf(),
    val isShowingTheMenu: Boolean = false,
    val expandTheMenu: Boolean = false,
    var isSnackBarShow: Boolean = false,
    var userRights: Int? = null,
    var userRightsToAddNote: Int? = null
) : BaseState()