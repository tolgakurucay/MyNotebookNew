package com.tolgakurucay.mynotebooknew.util

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale


fun Date.toSimpleString() : String {
    val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return format.format(this)
}


fun Long.toDate() : Date{
    return Date(this)
}