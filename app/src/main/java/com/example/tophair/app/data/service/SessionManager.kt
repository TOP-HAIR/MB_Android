package com.example.tophair.app.data.service

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.tokenDataStore: DataStore<Preferences> by preferencesDataStore(name = "token_prefs")
val Context.userIdDataStore: DataStore<Preferences> by preferencesDataStore(name = "userId_prefs")

object SessionManager {
    private val TOKEN_KEY = stringPreferencesKey("jwt_token")
    private val USER_ID_KEY = stringPreferencesKey("user_id")
    private lateinit var tokenDataStore: DataStore<Preferences>
    private lateinit var userIdDataStore: DataStore<Preferences>

    fun initialize(context: Context) {
        tokenDataStore = context.tokenDataStore
        userIdDataStore = context.userIdDataStore
    }

    suspend fun saveToken(token: String) {
        tokenDataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
            Log.d("Token", "Token salvo: $token")
        }
    }

    fun getTokenFlow(): Flow<String?> {
        return tokenDataStore.data.map { preferences ->
            preferences[TOKEN_KEY]
        }
    }

    suspend fun saveUserId(userId: String) {
        userIdDataStore.edit { preferences ->
            preferences[USER_ID_KEY] = userId
        }
    }

    fun getUserIdFlow(): Flow<String?> {
        return userIdDataStore.data.map { preferences ->
            preferences[USER_ID_KEY]
        }
    }
}