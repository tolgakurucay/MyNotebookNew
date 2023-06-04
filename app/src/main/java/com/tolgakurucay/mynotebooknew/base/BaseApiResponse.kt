package com.tolgakurucay.mynotebooknew.base

open class BaseApiResponse {
    var responseType: ResponseType? = null
    val messages: List<MessageModel>? = null
    var responseCode: Int? = null
}