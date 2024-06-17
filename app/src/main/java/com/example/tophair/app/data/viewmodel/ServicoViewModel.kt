package com.example.tophair.app.data.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tophair.app.data.api.ServicoApi
import com.example.tophair.app.data.entities.Servico
import com.example.tophair.app.data.service.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ServicoViewModel : ViewModel() {
    val empresaServicoList: MutableLiveData<List<Servico>> = MutableLiveData()
    val servicoEmpresa: MutableLiveData<Servico> = MutableLiveData()
    val apiToken: ServicoApi = RetrofitService.getApiServiceWithToken(ServicoApi::class.java)

    val erroApi = MutableLiveData("")
    val servicoLoader: MutableLiveData<Boolean> = MutableLiveData(true)

    fun getListaServicoEmpresa(idEmpresa: Int) {
        viewModelScope.launch {
            servicoLoader.postValue(true)
            try {
                val response = apiToken.getListaServicoEmpresa(idEmpresa)

                if (response.isSuccessful) {
                    val empresaBody = response.body()

                    empresaBody?.let {
                        empresaServicoList.postValue(it)
                    }
                } else {
                    Log.e("ServicoViewModel", "erro no getListaServicoEmpresa ${response}")
                    erroApi.postValue(response.errorBody()!!.string())
                }
            } catch (e: Exception) {
                Log.e("ServicoViewModel", "Error in getListaServicoEmpresa! ${e.message}")
                erroApi.postValue(e.message)
            } finally {
                servicoLoader.postValue(false)
            }
        }
    }

    fun getServicoEmpresa(idServico: Int) {
        viewModelScope.launch {
            servicoLoader.postValue(true)
            try {
                val response = apiToken.getServicoEmpresa(idServico)

                if (response.isSuccessful) {
                    val empresaBody = response.body()

                    empresaBody?.let {
                        servicoEmpresa.postValue(it)
                    }
                } else {
                    Log.e("ServicoViewModel", "erro no getServicoEmpresa ${response}")
                    erroApi.postValue(response.errorBody()!!.string())
                }
            } catch (e: Exception) {
                Log.e("ServicoViewModel", "Error in getServicoEmpresa! ${e.message}")
                erroApi.postValue(e.message)
            } finally {
                servicoLoader.postValue(false)
            }
        }
    }
}

