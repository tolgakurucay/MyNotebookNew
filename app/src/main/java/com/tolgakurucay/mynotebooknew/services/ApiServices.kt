package com.tolgakurucay.mynotebooknew.services

import com.tolgakurucay.mynotebooknew.Config
import com.tolgakurucay.mynotebooknew.services.register.RegisterRequest
import com.tolgakurucay.mynotebooknew.services.register.RegisterResponse

sealed class ApiServices<T>(
    val path: String,
    val request: Any? = null,
    val queryMap: Map<String, Any> = mapOf(), // for GET requests
    val finishScreen: Boolean = true,
    val requestType: RequestType = RequestType.POST,
) {

    //REGISTER
    class Register(request: RegisterRequest) : ApiServices<RegisterResponse>(
        "v1/accounts:signUp", request = request, queryMap = mapOf(
            Pair("key", API_KEY)
        )
    )




    companion object {
        var ENDPOINT = Config.ENDPOINT_RELEASE
        var API_KEY = Config.API_KEY
    }

}

enum class RequestType {
    POST, GET;
}
