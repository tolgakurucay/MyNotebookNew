package com.tolgakurucay.mynotebooknew.services

import com.tolgakurucay.mynotebooknew.Config

sealed class ApiServices<T>(
    val path: String,
    val request: Any? = null,
    val queryMap: Map<String, Int> = mapOf(), // for GET requests
    val finishScreen: Boolean = true,
    val requestType: RequestType = RequestType.POST,
) {

    //TEST
    class MainContent(page : Int) : ApiServices<TestResponse>(
        path = "quotes", requestType = RequestType.GET, queryMap = mapOf<String,Int>(Pair("page",page))

    )

    companion object {
        var ENDPOINT = Config.ENDPOINT_RELEASE
    }

}

enum class RequestType {
    POST, GET;
}
