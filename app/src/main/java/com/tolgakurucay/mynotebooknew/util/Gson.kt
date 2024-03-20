package com.tolgakurucay.mynotebooknew.util

import com.google.gson.Gson
import kotlin.reflect.KClass

fun <T> T.toJson(): String = Gson().toJson(this)
fun <T> String.fromJson(classType: Class<T>): T = Gson().fromJson(this, classType)

