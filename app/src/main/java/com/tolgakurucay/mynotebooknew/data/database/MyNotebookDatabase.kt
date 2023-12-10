package com.tolgakurucay.mynotebooknew.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel


@Database(
    entities = [NoteModel::class],
    version = 4,

    )
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

}