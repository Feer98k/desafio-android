package com.picpay.desafio.android.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.retrofit.service.PicPayService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val URL = "http://careers.picpay.com/tests/mobdev/"

class AppRetrofit {


    private val gson: Gson by lazy { GsonBuilder().create() }

    private val okHttp: OkHttpClient by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(URL)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
        val service: PicPayService by lazy {
        retrofit.create(PicPayService::class.java)
    }
}
