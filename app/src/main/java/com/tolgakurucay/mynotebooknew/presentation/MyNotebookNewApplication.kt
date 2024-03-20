package com.tolgakurucay.mynotebooknew.presentation

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.util.Log
import com.tolgakurucay.mynotebooknew.data.database.DataStoreManager
import com.tolgakurucay.mynotebooknew.util.AppLanguage
import com.tolgakurucay.mynotebooknew.util.setCurrentLanguage
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltAndroidApp
class MyNotebookNewApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()

    }
    private fun createNotificationChannel() {
        val notificationManager = getSystemService(NotificationManager::class.java)
        val channel =
            NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(channel)
    }

    companion object {
        const val CHANNEL_ID = "alarm_note_channel"
        const val CHANNEL_NAME = "Alarm Note Channel"
    }

}


