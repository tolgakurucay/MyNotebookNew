package com.tolgakurucay.mynotebooknew.util

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.compose.runtime.MutableState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import kotlin.reflect.KClass


inline fun <T1 : Any, T2 : Any, R : Any> safeLet(p1: T1?, p2: T2?, block: (T1, T2) -> R?): R? {
    return if (p1 != null && p2 != null) block(p1, p2) else null
}

inline fun <T1 : Any, T2 : Any, T3 : Any, R : Any> safeLet(
    p1: T1?,
    p2: T2?,
    p3: T3?,
    block: (T1, T2, T3) -> R?
): R? {
    return if (p1 != null && p2 != null && p3 != null) block(p1, p2, p3) else null
}

inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, R : Any> safeLet(
    p1: T1?,
    p2: T2?,
    p3: T3?,
    p4: T4?,
    block: (T1, T2, T3, T4) -> R?
): R? {
    return if (p1 != null && p2 != null && p3 != null && p4 != null) block(p1, p2, p3, p4) else null
}

inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, R : Any> safeLet(
    p1: T1?,
    p2: T2?,
    p3: T3?,
    p4: T4?,
    p5: T5?,
    block: (T1, T2, T3, T4, T5) -> R?
): R? {
    return if (p1 != null && p2 != null && p3 != null && p4 != null && p5 != null) block(
        p1,
        p2,
        p3,
        p4,
        p5
    ) else null
}


@Throws(IOException::class)
fun Context.readJsonAsset(fileName: String): String {
    val inputStream = assets.open(fileName)
    val size = inputStream.available()
    val buffer = ByteArray(size)
    inputStream.read(buffer)
    inputStream.close()
    return String(buffer, Charsets.UTF_8)
}

inline fun <reified T : Any> Context.fromJsonToModel(jsonFileName: String, clazz: KClass<T>): T? {
    val file = readJsonAsset(jsonFileName)
    val type = object : TypeToken<T>() {}.type
    return Gson().fromJson(file, type)
}


inline fun <reified T : Any> Context.fromJsonToList(
    jsonFileName: String,
    clazz: KClass<T>
): List<T>? {
    val file = readJsonAsset(jsonFileName)
    val type = object : TypeToken<List<T>>() {}.type
    return Gson().fromJson(file, type)
}

fun Any?.isNull(): Boolean {
    return this == null
}

fun Any?.isNotNull(): Boolean {
    return this != null
}

inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
    Build.VERSION.SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
}

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    Build.VERSION.SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}

fun <T> List<T>.mapButReplace(targetItem: T, newItem: T) = map {
    if (it == targetItem) {
        newItem
    } else {
        it
    }
}

fun Int?.orZero() : Int{
    this?.let {
        return it
    } ?: run {
        return 0
    }
}

fun Long?.orZero() : Long{
    this?.let {
        return it
    } ?: run {
        return 0L
    }
}

@JvmName("setNullableStateFalse")
fun MutableState<Boolean?>.setStateFalse(){
    value = false
}

@JvmName("setNullableStateTrue")
fun MutableState<Boolean?>.setStateTrue(){
    value = true
}

fun MutableState<Boolean>.setStateFalse(){
    value = false
}

fun MutableState<Boolean>.setStateTrue(){
    value = true
}

fun Uri?.toBitmap(context: Context): Bitmap? {
   this?.let {
       val source = ImageDecoder.createSource(context.contentResolver, this)
       return ImageDecoder.decodeBitmap(source)
   } ?: run {
       return null
   }
}

