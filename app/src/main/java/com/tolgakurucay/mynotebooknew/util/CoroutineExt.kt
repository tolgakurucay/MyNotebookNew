package com.tolgakurucay.mynotebooknew.util

import com.tolgakurucay.mynotebooknew.domain.base.BaseException
import com.tolgakurucay.mynotebooknew.domain.base.BaseState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import com.tolgakurucay.mynotebooknew.domain.base.Result
import kotlinx.coroutines.flow.catch


fun <T> CoroutineScope.callService(
    baseState: BaseState,
    success: suspend (data: T) -> Unit,
    service: suspend () -> Flow<Result<T>>,
    shouldShowDialog: Boolean = true,
    fail: suspend (exception: BaseException?) -> Unit = {
        baseState.myNotebookException = it
    }
) {

    launch {
        service()
            .onStart {
                if (shouldShowDialog) emit(Result.loading())
            }
            .catch {
                fail.invoke(BaseException(cause = it))
            }
            .collect {
                when (it.status) {
                    Result.Status.LOADING -> {
                        if (shouldShowDialog) baseState.isShowLoading = true
                    }

                    Result.Status.ERROR -> {
                        if (shouldShowDialog) baseState.isShowLoading = false
                        fail.invoke(it.exception)
                    }

                    Result.Status.SUCCESS -> {
                        if (shouldShowDialog) baseState.isShowLoading = false
                        it.data?.apply { success(this) }
                    }

                }
            }

    }
}


fun <T> CoroutineScope.callServiceOneShot(
    baseState: BaseState,
    success: suspend (data: T) -> Unit,
    service: suspend () -> Result<T>,
    shouldShowDialog: Boolean = true,
    fail: (exception: BaseException?) -> Unit = {
        baseState.myNotebookException = it
    }
) {
    launch {
        val runService = service.invoke()

        when (runService.status) {
            Result.Status.LOADING -> {
                if (shouldShowDialog) baseState.isShowLoading = true
            }

            Result.Status.ERROR -> {
                if (shouldShowDialog) baseState.isShowLoading = false
                fail.invoke(runService.exception)
            }

            Result.Status.SUCCESS -> {
                if (shouldShowDialog) baseState.isShowLoading = false
                runService.data?.apply { success(this) }
            }

        }
    }
}


