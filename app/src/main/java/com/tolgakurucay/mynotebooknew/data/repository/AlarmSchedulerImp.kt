package com.tolgakurucay.mynotebooknew.data.repository

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.tolgakurucay.mynotebooknew.data.broadcastReceivers.AlarmReceiver
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.repository.AlarmScheduler
import javax.inject.Inject

class AlarmSchedulerImp @Inject constructor(private val context: Context) : AlarmScheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    override fun schedule(model: NoteModel) {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("MODEL", model)
        }
        model.alarmDate?.let { safeDate->
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                safeDate,
                PendingIntent.getBroadcast(
                    context,
                    model.hashCode(),
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
            )
        }

    }

    override fun cancel(model: NoteModel) {
        PendingIntent.getBroadcast(
            context,
            model.hashCode(),
            Intent(context, AlarmReceiver::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }
}