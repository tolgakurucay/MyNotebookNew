package com.tolgakurucay.mynotebooknew.data.di

import android.content.Context
import com.tolgakurucay.mynotebooknew.data.repository.AlarmSchedulerImp
import com.tolgakurucay.mynotebooknew.domain.repository.AlarmScheduler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AlarmModule {

    @Singleton
    @Provides
    fun injectAlarmScheduler(@ApplicationContext context: Context): AlarmScheduler = AlarmSchedulerImp(context)


}