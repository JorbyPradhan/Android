package com.example.flowapi.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private val retrofit:Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BaseUrl.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api by lazy {
        retrofit.create(ApiService::class.java)
    }
}