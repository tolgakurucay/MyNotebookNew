package com.tolgakurucay.mynotebooknew.util

import com.tolgakurucay.mynotebooknew.domain.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import com.tolgakurucay.mynotebooknew.domain.model.Result


fun <T> CoroutineScope.callService(
    vm: BaseViewModel?,
    success: (data: T) -> Unit,
    service: suspend () -> Flow<Result<T>>,
    shouldShowDialog: Boolean = true,
    fail: (error: String?) -> Unit = { vm?.myNotebookError?.value =it}
) {
    launch {
        service()
            .onStart {
                if (shouldShowDialog) emit(Result.loading())
            }
            .collect {
                when (it.status) {
                    Result.Status.LOADING -> {
                        if (shouldShowDialog) vm?.showWaitingDialog(true)
                    }
                    Result.Status.ERROR -> {
                        if (shouldShowDialog) vm?.showWaitingDialog(false)
                        fail(it.errorMessage)
                    }
                    Result.Status.SUCCESS -> {
                        if (shouldShowDialog) vm?.showWaitingDialog(false)
                        it.data?.apply { success(this) }
                    }
                }
            }
    }
}