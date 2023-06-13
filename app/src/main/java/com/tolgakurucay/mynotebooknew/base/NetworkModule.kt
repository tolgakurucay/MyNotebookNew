package com.tolgakurucay.mynotebooknew.base

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.tolgakurucay.mynotebooknew.services.ApiServices
import com.tolgakurucay.mynotebooknew.services.MyNotebookNewService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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


    @Singleton
    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val builder =
            OkHttpClient.Builder()
                .readTimeout(200, TimeUnit.SECONDS)
                .connectTimeout(200, TimeUnit.SECONDS)

        builder.addInterceptor(
            ChuckerInterceptor.Builder(context).collector(ChuckerCollector(context)).build()
        )


        return builder.build()


    }


    @Singleton
    @Provides
    fun provideMyNotebookNewService(okHttpClient: OkHttpClient): MyNotebookNewService =
        Retrofit.Builder()
            .baseUrl(ApiServices.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MyNotebookNewService::class.java)


}