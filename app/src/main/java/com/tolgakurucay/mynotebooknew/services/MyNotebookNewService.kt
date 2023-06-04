package com.tolgakurucay.mynotebooknew.services

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface MyNotebookNewService {

    @GET("{path}")
    suspend fun get(
        @Path("path", encoded = true) path: String,
        @QueryMap queries: Map<String, Int>
    ): Response<JsonObject>


    @POST("{path}")
    suspend fun post(
        @Path("path", encoded = true) path: String,
        @Body request: Any
    ): Response<JsonObject>


}