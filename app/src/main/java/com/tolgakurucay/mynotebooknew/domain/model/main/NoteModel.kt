package com.tolgakurucay.mynotebooknew.domain.model.main

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "NoteTable")
data class NoteModel(
    var title: String?,
    var description: String?,
    @ColumnInfo(name = "image") var imageBase64: String?,
    var date: Long?,
    var noteType: String = NoteType.NOTE.name,
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
) {

}
