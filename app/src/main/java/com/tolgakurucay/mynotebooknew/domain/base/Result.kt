package com.tolgakurucay.mynotebooknew.domain.base


data class Result<out T>(
    val status: Status,
    val data: T?,
    val exception: BaseException?,
    val isFinishScreen: Boolean = false
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING,
    }

    companion object {
        fun <T> success(data: T): Result<T> {
            return Result(Status.SUCCESS, data, null)
        }

        fun <T> error(
            exception: BaseException?,
            data: T? = null,
            isFinishScreen: Boolean = false
        ): Result<T> {
            return Result(Status.ERROR,data,exception)
        }

        fun <T> loading(data: T? = null): Result<T> {
            return Result(Status.LOADING, data, null)
        }

    }
}