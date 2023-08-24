package com.tolgakurucay.mynotebooknew.data.di

import android.content.Context
import androidx.room.Room
import com.tolgakurucay.mynotebooknew.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(context,AppDatabase::class.java,"AppDatabase")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun injectDao(database: AppDatabase) = database.noteDao()


}