package com.tolgakurucay.mynotebooknew.domain.base

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson

class CustomNavType<T : Parcelable> constructor(private val clazz: Class<T>, isNullable: Boolean) :
    NavType<T>(isNullable) {
    override fun get(bundle: Bundle, key: String): T? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): T {
        return Gson().fromJson(value, clazz)
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putParcelable(key, value)
    }

}