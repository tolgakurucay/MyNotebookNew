package com.tolgakurucay.mynotebooknew.presentation.main.add_note

data class AddNoteState(
   var isAddedAlarm: Boolean = false,
   var isAddedCloud: Boolean = false,
   var isAddedFavorite: Boolean = false,
   var isAddedPhoto: Boolean = false,
   var hasCloudRight: Boolean = false
)
