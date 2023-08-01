package com.tolgakurucay.mynotebooknew.domain.model.main

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteModel(
    var title:String,
    var description:String,
    var imageBase64: String?,
    var date: Long,
    var id:Int?=null
) : Parcelable
