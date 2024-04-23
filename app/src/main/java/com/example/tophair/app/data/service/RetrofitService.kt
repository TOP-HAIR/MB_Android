package com.example.tophair.app.data.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    val BASE_URL_USER: String = "http://localhost:8080/api/"
    val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }

    inline fun <reified T> getApiService(serviceClass: Class<T>): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_USER)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(serviceClass)
    }
}