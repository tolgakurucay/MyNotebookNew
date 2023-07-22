package com.tolgakurucay.mynotebooknew.domain.model


sealed class ApiServices<T>(
    val path: String,
    val request: Any? = null,
    val queryMap: Map<String, Any> = mapOf(), // for GET requests
    val fieldMap: Map<String, String> = mapOf(), // for SET requests
    val finishScreen: Boolean = true,
    val requestType: RequestType = RequestType.POST,
) {






}

enum class RequestType {
    POST, GET;
}
