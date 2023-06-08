package com.tolgakurucay.mynotebooknew.base



open class BaseApiResponse {
    var responseType: ResponseType? = null
    val messages: List<MessageModel>? = null
    var responseCode: Int? = null
    val error : Error? = null
}


data class Error(
    val code: Int,
    val errors: List<ErrorX>,
    val message: String
)

data class ErrorX(
    val domain: String,
    val message: String,
    val reason: String
)