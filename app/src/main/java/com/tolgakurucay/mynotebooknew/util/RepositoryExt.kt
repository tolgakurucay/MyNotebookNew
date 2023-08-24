package com.tolgakurucay.mynotebooknew.util

import com.tolgakurucay.mynotebooknew.domain.base.BaseException
import com.tolgakurucay.mynotebooknew.domain.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

suspend inline fun <reified T> flowService(service: (Flow<T>)): Flow<Result<T>> = flow {
    service
        .onStart { emit(Result.loading()) }
        .catch { emit(Result.error(BaseException(cause = it))) }
        .collect { emit(Result.success(it)) }
}


suspend fun resultService(service: suspend () -> Unit): Result<Boolean> {
    return try {
        service.invoke()
        Result.success(true)
    } catch (ex: Exception) {
        Result.error(BaseException(cause = ex))
    }

}
