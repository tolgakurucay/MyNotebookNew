package com.tolgakurucay.mynotebooknew.presentation.main.add_note

import com.tolgakurucay.mynotebooknew.domain.base.BaseState

data class AddNoteState(
   var isAddedTheNote: Boolean?=false
) : BaseState()
