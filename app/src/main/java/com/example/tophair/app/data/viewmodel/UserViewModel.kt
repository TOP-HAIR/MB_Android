package com.example.tophair.app.data.viewmodel

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tophair.app.data.api.UserApi
import com.example.tophair.app.data.entities.User
import com.example.tophair.app.data.entities.UserCadastro
import com.example.tophair.app.data.entities.UserCadastroDeserealize
import com.example.tophair.app.data.entities.UserGet
import com.example.tophair.app.data.entities.UserLogin
import com.example.tophair.app.data.entities.UserUpdate
import com.example.tophair.app.data.service.RetrofitService
import com.example.tophair.app.data.service.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel : ViewModel() {
    val userAtual: MutableLiveData<Any> = MutableLiveData()
    val userId: MutableLiveData<Long> = MutableLiveData()
    val userVincular: MutableLiveData<Any> = MutableLiveData()
    val users = MutableLiveData(SnapshotStateList<User>())
    val user: MutableLiveData<UserGet> = MutableLiveData()
    val userCadastro: MutableLiveData<UserCadastro> = MutableLiveData()

    val apiUsers: UserApi = RetrofitService.getApiService(UserApi::class.java)
    val apiUsersToken: UserApi = RetrofitService.getApiServiceWithToken(UserApi::class.java)
    val erroApi = MutableLiveData("")

    init {
        getUser()
    }

    fun postUserLogin(login: UserLogin) {
        viewModelScope.launch {
            try {
                val response = apiUsers.postUserLogin(login)

                if (response.isSuccessful) {
                    val user = response.body()

                    user?.let {
                        userAtual.postValue(it)

                        val token = it.token
                        val userId = it.userId

                        SessionManager.saveToken(token.toString())
                        SessionManager.saveUserId(userId.toString())
                    }
                } else {
                    Log.e("UserViewModel", "erro no postUserLogin ${response}")
                    erroApi.postValue("Dados inválidos.")
                }
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error in postUserLogin! ${e.message}")
                erroApi.postValue(e.message)
            }
        }
    }

    fun postUserCadastro(cadastro: UserCadastroDeserealize) {
        viewModelScope.launch {
            try {
                val response = apiUsers.postUserCadastrar(cadastro)

                if (response.isSuccessful) {
                    val user = response.body()
                    Log.d("UserViewModel", "User response body: $user")
                    user?.let {
                        userId.postValue(it)
                    }
                } else {
                    Log.e("UserViewModel", "erro no postUserLogin ${response}")
                    erroApi.postValue(response.errorBody()?.string())
                }
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error in postUserLogin! ${e.message}")
                erroApi.postValue(e.message)
            }
        }
    }

    fun putUser(user: UserUpdate) {
        viewModelScope.launch {
            try {
                val userId = withContext(Dispatchers.Main) {
                    SessionManager.getUserIdFlow().firstOrNull()
                }
                if (!userId.isNullOrEmpty()) {
                    val response = apiUsersToken.updateUser(userId.toLong(), user)

                    if (response.isSuccessful) {
                        val user = response.body()

                        user?.let {
                            userAtual.postValue(it)
                        }
                    } else {
                        Log.e("UserViewModel", "erro no UserUpdate ${response}")
                        erroApi.postValue(response.errorBody()?.string())
                    }
                }
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error in UserUpdate! ${e.message}")
                erroApi.postValue(e.message)
            }
        }
    }

    fun putVincularUserEndereco(userId: Int? = null, enderecoId: Int? = null) {
        viewModelScope.launch {
            try {
                if (userId != null && enderecoId != null) {
                    val response = apiUsers.updateVincularEnderecoUser(
                        enderecoId.toLong(),
                        userId.toLong()
                    )
                    if (response.isSuccessful) {
                        val user = response.body()

                        user?.let {
                            userVincular.postValue(it)
                        }
                    } else {
                        Log.e("UserViewModel", "erro no putVincularUserEndereco ${response}")
                        erroApi.postValue(response.errorBody()?.string())
                    }
                }

            } catch (e: Exception) {
                Log.e("UserViewModel", "Error in putVincularUserEndereco! ${e.message}")
                erroApi.postValue(e.message)
            }
        }
    }

    fun getUser() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userId = withContext(Dispatchers.Main) {
                    SessionManager.getUserIdFlow().firstOrNull()
                }
                if (!userId.isNullOrEmpty()) {
                    val response = apiUsersToken.getUser(userId.toInt())

                    if (response.isSuccessful) {
                        val userBody = response.body()

                        userBody?.let {
                            user.postValue(it)
                        }
                    } else {
                        Log.e("UserViewModel", "erro no getUser ${response}")
                        erroApi.postValue(response.errorBody()!!.string())
                    }
                }
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error in getUser! ${e.message}")
                erroApi.postValue(e.message)
            }
        }
    }

    fun deleteUser() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userId = withContext(Dispatchers.Main) {
                    SessionManager.getUserIdFlow().firstOrNull()
                }

                if (!userId.isNullOrEmpty()) {
                    val response = apiUsersToken.deleteUser(userId.toLong())

                    if (response.isSuccessful) {

                    } else {
                        Log.e("UserViewModel", " erro no deleteUser ${response}")
                    }
                }
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error no delete de deleteUser: ${e.message}")
            }
        }
    }
}
