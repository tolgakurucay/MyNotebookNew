package com.tolgakurucay.mynotebooknew.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.toSimpleString() : String {
    val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return format.format(this)
}

fun currentTimeToLong(): Long {
    return System.currentTimeMillis()
}

fun String.convertDateToLong(): Long {
    val df = SimpleDateFormat("dd/MM/yyyy",Locale.getDefault())
    return df.parse(this)?.time ?: 0
}

fun Long.toSimpleString() : String{
    val date = Date(this)
    return date.toSimpleString()
}