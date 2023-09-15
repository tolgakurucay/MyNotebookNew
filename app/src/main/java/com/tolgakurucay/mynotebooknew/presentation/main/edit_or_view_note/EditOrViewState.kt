package com.tolgakurucay.mynotebooknew.presentation.main.edit_or_view_note

import android.os.Parcelable
import com.tolgakurucay.mynotebooknew.domain.base.BaseState
import kotlinx.parcelize.Parcelize

@Parcelize
data class EditOrViewState(var hasUpdated: Boolean = false) : BaseState(), Parcelable
