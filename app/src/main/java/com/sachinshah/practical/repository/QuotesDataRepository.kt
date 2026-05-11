package com.sachinshah.practical.repository

import com.sachinshah.practical.ApiResponse
import com.sachinshah.practical.model.LoginRequest
import com.sachinshah.practical.model.QuoteData
import com.sachinshah.practical.retrofit.ApiCall
import com.google.gson.JsonObject
import javax.inject.Inject

class QuotesDataRepository @Inject constructor (){

    @Inject
    lateinit var apiCall: ApiCall


    suspend fun getQuotes(
        page: Int
    ): ApiResponse<QuoteData?> {
        val response = apiCall.getQuotes(page)
        try {
            if (response.isSuccessful) {
                val data = response.body()
                return ApiResponse.Success(data)
            } else {
                return ApiResponse.Error(response.errorBody() as Throwable)
            }

        } catch (e: Exception) {
            return ApiResponse.Error(e)
        }


    }


    suspend fun login(
        loginRequest: LoginRequest
    ): ApiResponse<JsonObject?> {
        val response = apiCall.login(loginRequest)
        try {
            if (response.isSuccessful) {
                val data = response.body()
                return ApiResponse.Success(data)
            } else {
                return ApiResponse.Error(response.errorBody() as Throwable)
            }

        } catch (e: Exception) {
            return ApiResponse.Error(e)
        }


    }

}