package com.sachinshah.practical.mvi.repositroy

import com.sachinshah.practical.mvi.ApiService
import com.sachinshah.practical.mvi.ApiState
import com.sachinshah.practical.mvi.Ketro
import com.sachinshah.practical.mvi.LoginResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepository @Inject constructor(
   @Ketro val apiService: ApiService
) {

    suspend fun login(email: String,password: String): Boolean{

        delay(2000)
        return email=="gg@g.com" && password=="123456"
    }


    suspend fun loginViaApi(email: String,password: String) : Flow<ApiState<LoginResponse>> = flow {
        emit(ApiState.Loading)
        try {
            val response = apiService.login(email,password)

            if(response.isSuccessful&& response.body()!=null){
                response.body()?.let {
                    ApiState.Success(it)
                }
            }else emit(ApiState.Error(""))
        } catch (e: Exception) {
            emit(ApiState.Error(e.message ?:"Unknown Error"))
        }
    }

}