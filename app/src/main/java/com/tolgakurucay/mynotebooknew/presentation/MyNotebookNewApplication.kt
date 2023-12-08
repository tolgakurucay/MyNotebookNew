package com.tolgakurucay.mynotebooknew.presentation

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyNotebookNewApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }


    private fun createNotificationChannel() {
        val channelId = "alarm_note_channel"
        val channelName = "Alarm Note Channel"
        val notificationManager = getSystemService(NotificationManager::class.java)
        val channel =
            NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(channel)
    }
}


