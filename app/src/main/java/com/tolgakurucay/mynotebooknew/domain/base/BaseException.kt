package com.tolgakurucay.mynotebooknew.domain.base


class BaseException constructor(
    var exceptionType: ExceptionType? = null,
    var cause: Throwable?=null
)


enum class ExceptionType {
    SIGNIN,
    CREATE_EMAIL_PASSWORD
}




