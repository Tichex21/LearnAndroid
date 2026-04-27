package com.demo.myapplication.repository

import com.demo.myapplication.ApiResponse
import com.demo.myapplication.interfaces.PassAnyDataListener
import com.demo.myapplication.model.QuoteData
import com.demo.myapplication.retrofit.ApiInterface
import com.demo.myapplication.retrofit.ApiService

class QuotesDataRepository(val apiInterface: ApiInterface) {

    suspend fun getQuotes(
        page: Int
    ): QuoteData? {
        val response = apiInterface.getQuotes(page)
        if (response.isSuccessful) {
            return response.body()
        } else {
            throw Exception("API Error : ${response.code()}")
        }
    }

    suspend fun getQuotes2(
        page: Int
    ): ApiResponse<QuoteData?> {
        val response = apiInterface.getQuotes(page)
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