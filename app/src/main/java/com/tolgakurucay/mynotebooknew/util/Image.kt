package com.tolgakurucay.mynotebooknew.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream
import java.util.Base64

fun Bitmap?.toBase64(quality: Int = 100): String? {
    this?.let {
        val byteArrayOutputStream = ByteArrayOutputStream()
        this.compress(Bitmap.CompressFormat.PNG, quality, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.getEncoder().encodeToString(byteArray)

    } ?: run {
        return null
    }
}

fun String?.toBitmap(): Bitmap? {
    val decodedString = Base64.getDecoder().decode(this)
    return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
}


fun Bitmap?.makeSmallerBitmap(maximumSize: Int): Bitmap? {

    this?.let {
        val safeBitmap = this.copy(Bitmap.Config.ARGB_8888,true)
        val bitmapRatio: Double = safeBitmap.width.toDouble() / safeBitmap.height.toDouble()
        if (bitmapRatio > 1) {
            //landscape
            safeBitmap.width = maximumSize
            val scaledHeight = safeBitmap.width / bitmapRatio
            safeBitmap.height = scaledHeight.toInt()
        } else {
            //portrait
            safeBitmap.height = maximumSize
            val scaledWidth = height * bitmapRatio
            safeBitmap.width = scaledWidth.toInt()
        }
         return Bitmap.createScaledBitmap(this, safeBitmap.width, safeBitmap.height, true)
    } ?: kotlin.run {
        return null
    }


}

