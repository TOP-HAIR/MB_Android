package com.example.tophair.app.data.service

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import okhttp3.Request
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

object AuthInterceptor {
    private suspend fun getTokenFromDataStore(): String {
        Log.d("Token", "Token salvo: ${SessionManager.getTokenFlow().firstOrNull()}")
        return SessionManager.getTokenFlow().firstOrNull() ?: "seu_bearer_token_aqui"
    }

    fun interceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest: Request = chain.request()
            val token = runBlocking { getTokenFromDataStore() }
            val requestBuilder: Request.Builder = originalRequest.newBuilder()
                .header("Authorization", "Bearer $token")
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }
    }
}
