package com.sachinshah.practical.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sachinshah.practical.ApiResponse
import com.sachinshah.practical.model.LoginRequest
import com.sachinshah.practical.model.QuoteData
import com.sachinshah.practical.repository.QuotesDataRepository
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesListViewModel @Inject constructor(
    private val quotesDataRepository: QuotesDataRepository
) : ViewModel() {

    private val _quotesLiveData = MutableLiveData<ApiResponse<QuoteData?>>()
    val quotesLiveData: LiveData<ApiResponse<QuoteData?>> get() = _quotesLiveData

    private val _loginLiveData = MutableLiveData<ApiResponse<JsonObject?>>()
    val loginLiveData: LiveData<ApiResponse<JsonObject?>> get() = _loginLiveData


    fun fetchQuotes(page: Int = 1) {
        viewModelScope.launch {
            _quotesLiveData.value = ApiResponse.ShowLoading()
            try {
                val data = quotesDataRepository.getQuotes(page)
                _quotesLiveData.value = data
            } catch (e: Exception) {
                _quotesLiveData.value = ApiResponse.Error(e)
            } finally {
                _quotesLiveData.postValue(ApiResponse.HideLoading())
            }
        }

    }


    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            _loginLiveData.value = ApiResponse.ShowLoading()
            try {
                val data = quotesDataRepository.login(loginRequest)
                _loginLiveData.value = data
            } catch (e: Exception) {
                _loginLiveData.value = ApiResponse.Error(e)
            } finally {
                _loginLiveData.postValue(ApiResponse.HideLoading())
            }
        }

    }

}