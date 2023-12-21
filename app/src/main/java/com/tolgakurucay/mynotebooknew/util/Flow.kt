package com.tolgakurucay.mynotebooknew.util

import com.tolgakurucay.mynotebooknew.domain.base.BaseException
import com.tolgakurucay.mynotebooknew.domain.base.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

fun <T> Flow<T>.executeFlow() = flow<Result<T>> {
    onStart { emit(Result.loading()) }
        .onEach { emit(Result.success(it)) }
        .catch { emit(Result.error(exception = BaseException(cause = it))) }
        .collect()
}

