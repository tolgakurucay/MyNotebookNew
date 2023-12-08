package com.tolgakurucay.mynotebooknew.data.broadcastReceivers

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.util.orZero
import com.tolgakurucay.mynotebooknew.util.parcelable

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val model = intent?.parcelable<NoteModel>("MODEL") ?: return
        val channelId = "alarm_note_channel"
        context?.let { sContext->
            sContext.getSystemService(NotificationManager::class.java)?.let {nm->
                val builder = NotificationCompat.Builder(sContext,channelId)
                    .setSmallIcon(R.drawable.add_alarm)
                    .setContentTitle(model.title)
                    .setContentText(model.description)
                    .setPriority(NotificationManager.IMPORTANCE_HIGH)

                nm.notify(model.id.orZero(),builder.build())
            }
        }

    }
}