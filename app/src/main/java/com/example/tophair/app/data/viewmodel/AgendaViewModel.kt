package com.example.tophair.app.data.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tophair.app.data.api.AgendaApi
import com.example.tophair.app.data.entities.Agenda
import com.example.tophair.app.data.service.RetrofitService
import com.example.tophair.app.data.service.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AgendaViewModel : ViewModel() {
    val agendaAtual: MutableLiveData<Any> = MutableLiveData()
    val agenda: MutableLiveData<List<Agenda>> = MutableLiveData()

    val apiToken: AgendaApi = RetrofitService.getApiServiceWithToken(AgendaApi::class.java)
    val erroApi = MutableLiveData("")

    init {
        getAgendaUser()
    }

    fun getAgendaUser() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userId = withContext(Dispatchers.Main) {
                    SessionManager.getUserIdFlow().firstOrNull()
                }
                if (!userId.isNullOrEmpty()) {
                    val response = apiToken.getAgendaUser(userId.toInt())

                    if (response.isSuccessful) {
                        val agendaBody = response.body()

                        agendaBody?.let {
                            agenda.postValue(it)
                            Log.e("Erro", "erro agenda ${it}")
                        }

                    } else {
                        Log.e("AgendaViewModel", "erro no getAgendaUser ${response}")
                        erroApi.postValue(response.errorBody()!!.string())
                    }
                }
            } catch (e: Exception) {
                Log.e("AgendaViewModel", "Error in getAgendaUser! ${e.message}")
                erroApi.postValue(e.message)
            }
        }
    }
}