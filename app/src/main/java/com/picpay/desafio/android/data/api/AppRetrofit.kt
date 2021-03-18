package com.picpay.desafio.android.data.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.data.api.service.PicPayService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val URL = "http://careers.picpay.com/tests/mobdev/"

class AppRetrofit {


    private val gson: Gson by lazy { GsonBuilder().create() }

    private val okHttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
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
