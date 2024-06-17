package com.example.tophair.app.data.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tophair.app.data.api.EmpresaApi
import com.example.tophair.app.data.entities.Empresa
import com.example.tophair.app.data.entities.EmpresaPorId
import com.example.tophair.app.data.service.RetrofitService
import com.example.tophair.app.data.service.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EmpresaViewModel : ViewModel() {
    val empresaTop5: MutableLiveData<List<Empresa>> = MutableLiveData()
    val empresaFiltro: MutableLiveData<List<Empresa>> = MutableLiveData()
    val empresaHome: MutableLiveData<List<Empresa>> = MutableLiveData()
    val empresaGetId: MutableLiveData<EmpresaPorId?> = MutableLiveData()

    val apiToken: EmpresaApi = RetrofitService.getApiServiceWithToken(EmpresaApi::class.java)
    val erroApi = MutableLiveData("")
    val empresaLoader: MutableLiveData<Boolean> = MutableLiveData(true)

    init {
        getTop5MelhoresEmpresas()
        getHomeEmpresas()
        getFiltroEmpresas()
    }

    fun getTop5MelhoresEmpresas() {
        viewModelScope.launch {
            empresaLoader.postValue(true)
            try {
                val userId = SessionManager.getUserIdFlow().firstOrNull()
                if (!userId.isNullOrEmpty()) {
                    val response = apiToken.getTop5MelhoresEmpresas(userId.toInt())
                    if (response.isSuccessful) {
                        val empresaBody = response.body()
                        empresaTop5.value = empresaBody
                    } else {
                        Log.e("EmpresaViewModel", "erro no getTop5MelhoresEmpresas ${response}")
                        erroApi.value =
                            response.errorBody()?.string()
                    }
                }
            } catch (e: Exception) {
                Log.e("EmpresaViewModel", "Error in getTop5MelhoresEmpresas! ${e.message}")
                erroApi.value = e.message
            } finally {
                empresaLoader.postValue(false)
            }
        }
    }


    fun getEmpresaById(empresaId: Int?) {
        viewModelScope.launch {
            empresaLoader.postValue(true)
            try {
                val response = apiToken.getEmpresaPeloId(empresaId)
                if (response.isSuccessful) {
                    val empresa = response.body()
                    empresaGetId.value = empresa
                } else {
                    Log.e("EmpresaViewModel", "Erro no getEmpresaById ${response}")
                    erroApi.value =
                        response.errorBody()?.string()
                }
            } catch (e: Exception) {
                Log.e("EmpresaViewModel", "Erro no getEmpresaById! ${e.message}")
                erroApi.value = e.message
            } finally {
                empresaLoader.postValue(false)
            }
        }
    }


    fun getHomeEmpresas() {
        viewModelScope.launch {
            empresaLoader.postValue(true)
            try {
                val userId = SessionManager.getUserIdFlow().firstOrNull()
                if (!userId.isNullOrEmpty()) {
                    val response = apiToken.getTop5MelhoresEmpresas(userId.toInt())
                    if (response.isSuccessful) {
                        val empresaBody = response.body()
                        empresaHome.value = empresaBody
                    } else {
                        Log.e("EmpresaViewModel", "erro no getTop5MelhoresEmpresas ${response}")
                        erroApi.value =
                            response.errorBody()?.string()
                    }
                }
            } catch (e: Exception) {
                Log.e("EmpresaViewModel", "Error in getTop5MelhoresEmpresas! ${e.message}")
                erroApi.value = e.message
            } finally {
                empresaLoader.postValue(false)
            }
        }
    }

    fun getFiltroEmpresas(estado: String = "", servico: String = "", nomeEmpresa: String = "") {
        viewModelScope.launch {
            try {
                val userId = SessionManager.getUserIdFlow().firstOrNull()
                if (!userId.isNullOrEmpty()) {
                    val response =
                        apiToken.getFiltroEmpresas(userId.toInt(), estado, servico, nomeEmpresa)

                    if (response.isSuccessful) {
                        clearEmpresaFiltro()

                        val empresaBody = response.body()
                        empresaBody?.let {
                            empresaFiltro.value = it
                        }

                    } else {
                        Log.e("EmpresaViewModel", "erro no getFiltroEmpresas ${response}")
                        erroApi.value = response.errorBody()?.string()
                    }
                }
            } catch (e: Exception) {
                Log.e("EmpresaViewModel", "Error in getFiltroEmpresas! ${e.message}")
                erroApi.value = e.message
            }
        }
    }

    fun clearEmpresaFiltro() {
        empresaFiltro.value = emptyList()
    }
}