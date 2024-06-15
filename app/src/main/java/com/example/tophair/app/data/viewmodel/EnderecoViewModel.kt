package com.example.tophair.app.data.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tophair.app.data.api.EnderecoApi
import com.example.tophair.app.data.entities.Endereco
import com.example.tophair.app.data.entities.EnderecoResponse
import com.example.tophair.app.data.service.RetrofitService
import kotlinx.coroutines.launch

class EnderecoViewModel : ViewModel() {
    val endereco: MutableLiveData<EnderecoResponse> = MutableLiveData()

    val enderecoUser: EnderecoApi = RetrofitService.getApiService(EnderecoApi::class.java)
    val erroApi = MutableLiveData("")

    fun postEndereco(enderecoValue: Endereco) {
        viewModelScope.launch {
            try {
                val response = enderecoUser.postEndereco(enderecoValue)

                if (response.isSuccessful) {
                    val enderecoResponse = response.body()
                    Log.d("EnderecoViewModel", "Endereco response body: $enderecoResponse")
                    enderecoResponse?.let {
                        endereco.postValue(it)
                    }
                } else {
                    Log.e("EnderecoViewModel", "erro no postEndereco ${response}")
                    erroApi.postValue("Dados inválidos.")
                }
            } catch (e: Exception) {
                Log.e("EnderecoViewModel", "Error in postEndereco! ${e.message}")
                erroApi.postValue(e.message)
            }
        }
    }
}