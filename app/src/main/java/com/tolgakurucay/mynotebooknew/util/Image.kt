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
        val bitmapRatio: Double = width.toDouble() / height.toDouble()
        if (bitmapRatio > 1) {
            //landscape
            width = maximumSize
            val scaledHeight = width / bitmapRatio
            height = scaledHeight.toInt()
        } else {
            //portrait
            height = maximumSize
            val scaledWidth = height * bitmapRatio
            width = scaledWidth.toInt()
        }
        return Bitmap.createScaledBitmap(this, width, height, true)
    } ?: kotlin.run {
        return null
    }


}