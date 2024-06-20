package com.example.tophair.app.data.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tophair.app.data.api.AgendaApi
import com.example.tophair.app.data.entities.AgendaCancelado
import com.example.tophair.app.data.entities.AgendaEmpresa
import com.example.tophair.app.data.entities.AgendaEmpresaDto
import com.example.tophair.app.data.entities.AgendaPost
import com.example.tophair.app.data.entities.AgendaResponse
import com.example.tophair.app.data.entities.AgendaServico
import com.example.tophair.app.data.entities.UsuarioAgenda
import com.example.tophair.app.data.service.RetrofitService
import com.example.tophair.app.data.service.SessionManager
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class AgendaViewModel : ViewModel() {
    val agendaAtual: MutableLiveData<Any> = MutableLiveData()
    val agenda: MutableLiveData<List<AgendaEmpresaDto>> = MutableLiveData()
    val agendaRes: MutableLiveData<AgendaResponse> = MutableLiveData()
    val agendaCan: MutableLiveData<AgendaCancelado> = MutableLiveData()
    val vincularUsuario: MutableLiveData<UsuarioAgenda> = MutableLiveData()
    val vincularServico: MutableLiveData<AgendaServico> = MutableLiveData()
    val vincularEmpresa: MutableLiveData<AgendaEmpresa> = MutableLiveData()

    val apiToken: AgendaApi = RetrofitService.getApiServiceWithToken(AgendaApi::class.java)
    val erroApi = MutableLiveData("")
    val agendaLoader: MutableLiveData<Boolean> = MutableLiveData(true)

    init {
        getAgendaUser()
    }

    fun postAgenda(agendaValue: AgendaPost, idEmpresa: Int, idServico: Int) {
        viewModelScope.launch {
            try {
                val userId = SessionManager.getUserIdFlow().firstOrNull()
                if (!userId.isNullOrEmpty()) {
                    val response = apiToken.postAgenda(agendaValue, idEmpresa, userId.toInt(), idServico)

                    if (response.isSuccessful) {
                        val agendaResponse = response.body()

                        agendaResponse?.let {
                            agendaRes.postValue(it)
                        }
                        Log.e("postAgenda", "postAgenda ${response}")
                    } else {
                        Log.e("EnderecoViewModel", "erro no postAgenda ${response}")
                        erroApi.postValue("Dados inválidos.")
                    }
                }
            } catch (e: Exception) {
                Log.e("EnderecoViewModel", "Error in postAgenda! ${e.message}")
                erroApi.postValue(e.message)
            }
        }
    }

    fun getAgendaUser() {
        viewModelScope.launch {
            agendaLoader.value = true
            try {
                val userId = SessionManager.getUserIdFlow().firstOrNull()
                if (!userId.isNullOrEmpty()) {
                    val response = apiToken.getAgendaUser(userId.toInt())

                    if (response.isSuccessful) {
                        val agendaBody = response.body()
                        agendaBody?.let {
                            agenda.value = it
                        }
                        Log.e("AgendaViewModel", "sucesso no getAgendaUser ${agenda}")
                    } else {
                        Log.e("AgendaViewModel", "erro no getAgendaUser ${response}")
                        erroApi.value = response.errorBody()?.string()
                    }
                }
            } catch (e: Exception) {
                Log.e("AgendaViewModel", "Error in getAgendaUser! ${e.message}")
                erroApi.value = e.message
            } finally {
                agendaLoader.value = false
            }
        }
    }

    fun putAgendaVincularUsuario(idAgenda: Int?) {
        viewModelScope.launch {
            agendaLoader.value = true
            try {
                val userId = SessionManager.getUserIdFlow().firstOrNull()
                if (!userId.isNullOrEmpty()) {
                    val response = apiToken.putAgendaVincularUsuario(idAgenda, userId.toInt())

                    if (response.isSuccessful) {
                        val agendaBody = response.body()
                        agendaBody?.let {
                            vincularUsuario.value = it
                        }
                        Log.e("putAgendaVincularUsuario", "${vincularUsuario}")
                    } else {
                        Log.e("AgendaViewModel", "erro no putAgendaVincularUsuario ${response}")
                        erroApi.value = response.errorBody()?.string()
                    }
                }
            } catch (e: Exception) {
                Log.e("AgendaViewModel", "Error in putAgendaVincularUsuario! ${e.message}")
                erroApi.value = e.message
            } finally {
                agendaLoader.value = false
            }
        }
    }

    fun putAgendaVincularServico(idAgenda: Int?, idServico: Int?) {
        viewModelScope.launch {
            agendaLoader.value = true
            try {

                val response = apiToken.putAgendaVincularServico(idAgenda, idServico)

                if (response.isSuccessful) {
                    val agendaBody = response.body()
                    agendaBody?.let {
                        vincularServico.value = it
                    }
                    Log.e("putAgendaVincularServico", "${vincularServico}")
                } else {
                    Log.e("AgendaViewModel", "erro no putAgendaVincularServico ${response}")
                    erroApi.value = response.errorBody()?.string()
                }

            } catch (e: Exception) {
                Log.e("AgendaViewModel", "Error in putAgendaVincularServico! ${e.message}")
                erroApi.value = e.message
            } finally {
                agendaLoader.value = false
            }
        }
    }

    fun putAgendaVincularEmpresa(idAgenda: Int?, idEmpresa: Int?) {
        viewModelScope.launch {
            agendaLoader.value = true
            try {
                val response = apiToken.putAgendaVincularEmpresa(idAgenda, idEmpresa)

                if (response.isSuccessful) {
                    val agendaBody = response.body()
                    agendaBody?.let {
                        vincularEmpresa.value = it
                    }
                    Log.e("putAgendaVincularEmpresa", "${vincularEmpresa}")
                } else {
                    Log.e("AgendaViewModel", "erro no putAgendaVincularEmpresa ${response}")
                    erroApi.value = response.errorBody()?.string()
                }

            } catch (e: Exception) {
                Log.e("AgendaViewModel", "Error in putAgendaVincularEmpresa! ${e.message}")
                erroApi.value = e.message
            } finally {
                agendaLoader.value = false
            }
        }
    }

    fun putCancelarAgenda(idAgenda: Int?) {
        viewModelScope.launch {
            agendaLoader.value = true
            try {
                val response = apiToken.putCancelarAgenda(idAgenda)

                if (response.isSuccessful) {
                    val agendaBody = response.body()
                    agendaBody?.let {
                        agendaCan.value = it
                    }
                    Log.e("putAgendaVincularEmpresa", "${vincularEmpresa}")
                } else {
                    Log.e("AgendaViewModel", "erro no putAgendaVincularEmpresa ${response}")
                    erroApi.value = response.errorBody()?.string()
                }

            } catch (e: Exception) {
                Log.e("AgendaViewModel", "Error in putAgendaVincularEmpresa! ${e.message}")
                erroApi.value = e.message
            } finally {
                agendaLoader.value = false
            }
        }
    }
}