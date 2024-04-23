package com.example.tophair.app.data.viewmodel

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tophair.app.data.api.UserApi
import com.example.tophair.app.data.entities.User
import com.example.tophair.app.data.entities.UserLogin
import com.example.tophair.app.data.service.RetrofitService
import com.example.tophair.app.data.service.RetrofitService.getApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {

    val userAtual = MutableLiveData(User())
    val users = MutableLiveData(SnapshotStateList<User>())

    val apiUsers: UserApi = RetrofitService.getApiService(UserApi::class.java)
    val erroApi = MutableLiveData("")

    fun postUserLogin(login: UserLogin) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiUsers.postUserLogin(login)
                if (response.isSuccessful) {
                    val user = response.body()
                    user?.let {
                        userAtual.postValue(it)
                    }
                } else {
                    Log.e("api", "erro no postUserLogin")
                    erroApi.postValue(response.errorBody()!!.string())
                }
            } catch (e: Exception) {
                Log.e("api", "Error in postUserLogin! ${e.message}")
                erroApi.postValue(e.message)
            }
        }
    }
}
