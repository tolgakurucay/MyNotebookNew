package com.tolgakurucay.mynotebooknew.base

import android.content.Context
import com.tolgakurucay.mynotebooknew.services.ApiServices
import com.tolgakurucay.mynotebooknew.services.MyNotebookNewService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


//    private val READ_TIME_OUT = if (BuildConfig.DEBUG) 200.toLong() else 60.toLong()
//    private val CONNECT_TIME_OUT = if (BuildConfig.DEBUG) 200.toLong() else 60.toLong()


    fun provideOkHttpClient(context: Context) : OkHttpClient{
        var builder =
            OkHttpClient.Builder()
                .readTimeout(200, TimeUnit.SECONDS)
                .connectTimeout(200, TimeUnit.SECONDS)
                .addInterceptor {
                    val original = it.request()
                    val reqBuilder = original.newBuilder()
                        .addHeader("id", "name" ?: "")
                    it.proceed(reqBuilder.build())

                }.build()

        return builder


    }


    @Singleton
    @Provides
    fun provideMyNotebookNewService(): MyNotebookNewService = Retrofit.Builder()
        .baseUrl(ApiServices.ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MyNotebookNewService::class.java)


}