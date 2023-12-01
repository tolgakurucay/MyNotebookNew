package com.tolgakurucay.mynotebooknew.util

import com.tolgakurucay.mynotebooknew.domain.base.BaseException
import com.tolgakurucay.mynotebooknew.domain.base.BaseState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import com.tolgakurucay.mynotebooknew.domain.base.Result
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach


fun <T> CoroutineScope.callService(
    baseState: BaseState,
    success: (data: T) -> Unit,
    service: suspend () -> Flow<Result<T>>,
    fail: (exception: BaseException?) -> Unit = { baseException ->
        baseException?.let { safeBaseException ->
            baseState.myNotebookException.value = safeBaseException
        }
    }
) {
    launch {
        service.invoke()
            .onEach {
                when (it.status) {
                    Result.Status.LOADING -> baseState.isShowLoading.value = true

                    Result.Status.ERROR -> {
                        baseState.isShowLoading.value = false
                        fail.invoke(it.exception)
                    }

                    Result.Status.SUCCESS -> {
                        baseState.isShowLoading.value = false
                        it.data?.apply { success(this) }
                    }
                }
            }.collect()


    }

}



