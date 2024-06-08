package com.example.tophair.app.data.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tophair.app.data.api.ServicoApi
import com.example.tophair.app.data.entities.Servico
import com.example.tophair.app.data.service.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ServicoViewModel : ViewModel() {
    val empresaServicoList: MutableLiveData<List<Servico>> = MutableLiveData()
    val apiToken: ServicoApi = RetrofitService.getApiServiceWithToken(ServicoApi::class.java)
    val erroApi = MutableLiveData("")

    fun getServicoEmpresa(idEmpresa: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiToken.getServicoEmpresa(idEmpresa)

                if (response.isSuccessful) {
                    val empresaBody = response.body()

                    empresaBody?.let {
                        empresaServicoList.postValue(it)
                    }
                } else {
                    Log.e("ServicoViewModel", "erro no getServicoEmpresa ${response}")
                    erroApi.postValue(response.errorBody()!!.string())
                }
            } catch (e: Exception) {
                Log.e("ServicoViewModel", "Error in getServicoEmpresa! ${e.message}")
                erroApi.postValue(e.message)
            }
        }
    }
}

