package com.example.tophair.app.data.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    val BASE_URL_USER: String = "http://34.237.189.174/api/"
    val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }
    val okHttpClientToken: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor.interceptor())
            .build()
    }


    inline fun <reified T> getApiService(serviceClass: Class<T>): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_USER)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(serviceClass)
    }

    inline fun <reified T> getApiServiceWithToken(serviceClass: Class<T>): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_USER)
            .client(okHttpClientToken)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(serviceClass)
    }
}