package com.tolgakurucay.mynotebooknew.domain.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import java.lang.Exception


open class BaseUseCase {

    fun <T> executeFlow(responseFromRepository: Flow<T>) = flow<Result<T>> {
        responseFromRepository
            .onStart {
                emit(Result.loading())
            }
            .onEach {
                emit(Result.success(it))
            }
            .catch {
                emit(Result.error(exception = BaseException(cause = it)))
            }
            .collect()
    }


    fun <T> execute(responseFromRepository: T) = flow {
        try {
            emit(Result.success(responseFromRepository))
        } catch (ex: Exception) {
            emit(Result.error(exception = BaseException(cause = ex.cause)))
        }
    }

}