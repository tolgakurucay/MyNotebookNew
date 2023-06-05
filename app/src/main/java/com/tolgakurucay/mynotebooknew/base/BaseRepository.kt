package com.tolgakurucay.mynotebooknew.base

import android.util.Log
import com.google.gson.Gson
import com.tolgakurucay.mynotebooknew.base.throwable.BaseThrowable
import com.tolgakurucay.mynotebooknew.services.ApiServices
import com.tolgakurucay.mynotebooknew.services.MyNotebookNewService
import com.tolgakurucay.mynotebooknew.services.RequestType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import com.tolgakurucay.mynotebooknew.services.Result
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject

open class BaseRepository @Inject constructor(val myNotebookNewService: MyNotebookNewService) {

    protected suspend inline fun <reified T> request(
        service: ApiServices<T>,
        showLoading: Boolean = true
    ): Flow<Result<T>> = flow {
        if (showLoading) emit(Result.loading())
        try {
            val raw = if (service.requestType == RequestType.POST) {
                myNotebookNewService.post(service.path, service.request ?: "")
            } else {
                myNotebookNewService.get(service.path, service.queryMap)
            }

            val response = Gson().fromJson(raw.body().toString(), T::class.java)
            emit(Result.success(response as T))
//            when ((response as BaseApiResponse).responseType) {
//                ResponseType.OK -> emit(Result.success(response as T))
//                ResponseType.WARNING -> emit(
//                    Result.error(
//                        BaseThrowable(
//                            uiMessage = response.messages,
//                            finishScreen = service.finishScreen,
//                            responseCode = response.responseCode
//                        )
//                    )
//                )
//                ResponseType.NO_CONTENT -> emit(Result.success(response as T))
//                else -> emit(
//                    Result.error(
//                        BaseThrowable(
//                            uiMessage = response.messages,
//                            finishScreen = service.finishScreen
//                        )
//                    )
//                )
//            }
        } catch (e: Exception) {
            Log.e("ServiceRepo ERROR-LOG", e.localizedMessage ?: "")
            emit(Result.error(BaseThrowable()))
        }
    }.catch { e ->
        e.printStackTrace()
       // FirebaseCrashlytics.getInstance().log(e.localizedMessage ?: "Throwable")
        if (e is BaseThrowable) {
            emit(Result.error(e))
        } else {
            emit(Result.error(BaseThrowable(finishScreen = service.finishScreen)))
        }
    }
}