package com.tolgakurucay.mynotebooknew.domain.repository

import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel

interface AlarmScheduler {
    fun schedule(model: NoteModel)
    fun cancel(model: NoteModel)
}