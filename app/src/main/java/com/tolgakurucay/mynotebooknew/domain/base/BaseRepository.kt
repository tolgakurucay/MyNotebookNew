package com.tolgakurucay.mynotebooknew.domain.base

import android.util.Log
import com.google.gson.Gson
import com.tolgakurucay.mynotebooknew.domain.model.ApiServices
import com.tolgakurucay.mynotebooknew.domain.model.MyNotebookNewService
import com.tolgakurucay.mynotebooknew.domain.model.RequestType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import com.tolgakurucay.mynotebooknew.domain.model.Result
import javax.inject.Inject

open class BaseRepository @Inject constructor(val myNotebookNewService: MyNotebookNewService) {

    companion object {
        const val TAG = "bilgitolga"
    }

    protected suspend inline fun <reified T> request(
        service: ApiServices<T>,
        showLoading: Boolean = true
    ): Flow<Result<T>> = flow {
        if (showLoading) emit(Result.loading())

        try {
            val raw = if (service.requestType == RequestType.POST) {
                myNotebookNewService.post(
                    service.path,
                    service.request ?: "",
                    fields = service.fieldMap
                )
            } else {
                myNotebookNewService.get(service.path, queries = service.queryMap)
            }

            if (raw.isSuccessful) {
                val result = Gson().fromJson(raw.body(), T::class.java)
                Log.d(TAG, "Successful body :$result ")
                emit(Result.success(result))
            } else {
                val response = Gson().fromJson(raw.errorBody()?.string(), BaseErrorResponse::class.java)
                Log.d(TAG, "Error body :${response}")
                emit(Result.error(Throwable(message = response.error.message)))
            }


        } catch (e: Exception) {
            Log.d(TAG, "Localized ${e.localizedMessage}")
            Log.d(TAG, "Normal Message ${e.message}")
            emit(Result.error(Throwable(message = e.message)))
        }
    }.catch { e ->
        e.printStackTrace()
        // FirebaseCrashlytics.getInstance().log(e.localizedMessage ?: "Throwable")
    }
}