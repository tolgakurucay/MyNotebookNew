package com.tolgakurucay.mynotebooknew.util

import com.tolgakurucay.mynotebooknew.domain.base.BaseException
import com.tolgakurucay.mynotebooknew.domain.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import com.tolgakurucay.mynotebooknew.domain.model.Result


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
            .collect {
                when (it.status) {
                    Result.Status.LOADING -> {
                        if (shouldShowDialog) baseViewModel?.showWaitingDialog(true)
                    }

                    Result.Status.ERROR -> {
                        if (shouldShowDialog) baseViewModel?.showWaitingDialog(false)
                        fail(it.exception)
                    }

                    Result.Status.SUCCESS -> {
                        if (shouldShowDialog) baseViewModel?.showWaitingDialog(false)
                        it.data?.apply { success(this) }
                    }

                }
            }
    }
}