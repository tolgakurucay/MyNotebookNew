package com.tolgakurucay.mynotebooknew.services

import com.tolgakurucay.mynotebooknew.base.throwable.BaseThrowable


data class Result<out T>(
    val status: Status,
    val data: T?,
    val error: BaseThrowable?,
    val isFinishScreen: Boolean = false
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): Result<T> {
            return Result(Status.SUCCESS, data, null)
        }

        fun <T> error(
            error: BaseThrowable,
            data: T? = null,
            isFinishScreen: Boolean = false
        ): Result<T> {
            return Result(Status.ERROR, data, error, isFinishScreen)
        }

        fun <T> loading(data: T? = null): Result<T> {
            return Result(Status.LOADING, data, null)
        }
    }
}