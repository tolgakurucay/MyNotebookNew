package com.tolgakurucay.mynotebooknew.util

import com.tolgakurucay.mynotebooknew.domain.base.BaseException
import com.tolgakurucay.mynotebooknew.domain.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import com.tolgakurucay.mynotebooknew.domain.model.Result
import kotlinx.coroutines.flow.catch


fun <T> CoroutineScope.callService(
    baseViewModel: BaseViewModel?,
    success: suspend (data: T) -> Unit,
    service: suspend () -> Flow<Result<T>>,
    shouldShowDialog: Boolean = true,
    fail: suspend (exception: BaseException?) -> Unit = {
        baseViewModel?.myNotebookException?.value = it
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
                        if (shouldShowDialog) baseViewModel?.showWaitingDialog(true)
                    }

                    Result.Status.ERROR -> {
                        if (shouldShowDialog) baseViewModel?.showWaitingDialog(false)
                        fail.invoke(it.exception)
                    }

                    Result.Status.SUCCESS -> {
                        if (shouldShowDialog) baseViewModel?.showWaitingDialog(false)
                        it.data?.apply { success(this) }
                    }

                }
            }

    }
}


fun <T> CoroutineScope.callServiceOneShot(
    baseViewModel: BaseViewModel?,
    success: (data: T) -> Unit,
    service: suspend () -> Result<T>,
    shouldShowDialog: Boolean = true,
    fail: (exception: BaseException?) -> Unit = {
        baseViewModel?.myNotebookException?.value = it
    }
) {
    launch {
        val runService = service.invoke()

        when (runService.status) {
            Result.Status.LOADING -> {
                if (shouldShowDialog) baseViewModel?.showWaitingDialog(true)
            }

            Result.Status.ERROR -> {
                if (shouldShowDialog) baseViewModel?.showWaitingDialog(false)
                fail.invoke(runService.exception)
            }

            Result.Status.SUCCESS -> {
                if (shouldShowDialog) baseViewModel?.showWaitingDialog(false)
                runService.data?.apply { success(this) }
            }

        }
    }
}


