package com.tolgakurucay.mynotebooknew.extensions

fun String?.isNull() : Boolean{
    return this==null
}

fun String?.isNotNull() : Boolean{
    return this!=null
}
