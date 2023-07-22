package com.tolgakurucay.mynotebooknew.domain.base



data class BaseErrorResponse(val error : Error)




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