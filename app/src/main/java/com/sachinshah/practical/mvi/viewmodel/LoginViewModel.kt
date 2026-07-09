package com.sachinshah.practical.mvi.viewmodel

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sachinshah.practical.mvi.ApiState
import com.sachinshah.practical.mvi.LoginIntent
import com.sachinshah.practical.mvi.LoginResponse
import com.sachinshah.practical.mvi.LoginState
import com.sachinshah.practical.mvi.repositroy.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) : ViewModel() {


    private val _state = MutableStateFlow(LoginState())
    val state =_state.asStateFlow()


    fun processIntents(intent: LoginIntent){

        when(intent){

            is LoginIntent.EmailChanged->{

                _state.update {
                    it.copy(
                        email = intent.email
                    )
                }
            }
            is LoginIntent.PasswordChange->{
                _state.update {
                    it.copy(
                        password = intent.password
                    )
                }
            }
            is LoginIntent.LoginClicked->{
                loginViaApi()
            }
        }
    }

    private fun login() {
        viewModelScope.launch {

            _state.update {
                it.copy(
                    isLoading=true,
                    isError = null
                )
            }

           val  result= loginRepository.login(
                _state.value.email,
                _state.value.password,

            )
            if(result){
                _state.update { it.copy(isLoading = false, isLoggedIn = true) }
            }else {
                _state.update { it.copy(isLoading = false, isError = "Invalid credentials") }
            }

        }
    }


    private fun loginViaApi(){

        viewModelScope.launch {
          val login=  loginRepository.loginViaApi(_state.value.email,_state.value.password)
            login.collect { result->
                when(result){

                    is ApiState.Loading->{
                        _state.update {
                            it.copy(
                                isLoading=true,
                                isError = null
                            )
                        }
                    }
                    is ApiState.Success->{
                        _state.update {
                            it.copy(
                                isLoading = false,
                                loginResponse = result.data
                            )
                        }
                    }
                    is ApiState.Error->{
                        _state.update { it.copy(isLoading = false, isError = result.message, loginResponse = LoginResponse(
                            "rew","rwerw"
                        )) }
                    }

                }

            }
        }
    }



    suspend fun exrte(){

        val job=   CoroutineScope(Dispatchers.IO).launch {
            delay(3212)

        }
        job.join()
        Log.e("rwe","rwe")


        val rewr= CoroutineScope(Dispatchers.IO).async {
            if(isActive)
            delay(3212)
            "rwwe"
        }
        Log.e("rwe",rewr.await())

        supervisorScope {

        }
    }


}