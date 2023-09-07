package com.tolgakurucay.mynotebooknew.domain.model.main

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NoteTable")
data class NoteModel(
    var title: String? = null,
    var description: String? = null,
    @ColumnInfo(name = "image") var imageBase64: String? = null,
    var date: Long? = null,
    var noteType: String = NoteType.NOTE.name,
    var alarmDate: Long? = null,
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
) {

}
