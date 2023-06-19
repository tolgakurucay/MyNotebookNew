package com.tolgakurucay.mynotebooknew.services

import com.tolgakurucay.mynotebooknew.BuildConfig
import com.tolgakurucay.mynotebooknew.services.register.RegisterRequest
import com.tolgakurucay.mynotebooknew.services.register.RegisterResponse

sealed class ApiServices<T>(
    val path: String,
    val request: Any? = null,
    val queryMap: Map<String, Any> = mapOf(), // for GET requests
    val fieldMap: Map<String, String> = mapOf(), // for SET requests
    val finishScreen: Boolean = true,
    val requestType: RequestType = RequestType.POST,
) {

    //REGISTER
    class Register(request: RegisterRequest) : ApiServices<RegisterResponse>(
        path = "v1/accounts:signUp", request = request,
        fieldMap = mapOf(
            Pair("key",BuildConfig.API_KEY),
        ),
    )




}

enum class RequestType {
    POST, GET;
}
