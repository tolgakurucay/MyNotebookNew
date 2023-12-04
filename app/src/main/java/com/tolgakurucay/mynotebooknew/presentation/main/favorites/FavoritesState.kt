package com.tolgakurucay.mynotebooknew.presentation.main.favorites

import com.tolgakurucay.mynotebooknew.domain.base.BaseState
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel

data class FavoritesState(val list: List<NoteModel> = listOf()) : BaseState()
