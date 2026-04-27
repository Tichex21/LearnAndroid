package com.demo.myapplication.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.myapplication.ApiResponse
import com.demo.myapplication.interfaces.PassAnyDataListener
import com.demo.myapplication.model.QuoteData
import com.demo.myapplication.model.QuoteModel
import com.demo.myapplication.repository.QuotesDataRepository
import com.demo.myapplication.retrofit.ApiInterface
import com.demo.myapplication.retrofit.ApiService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

class QuotesListViewModel(apiService: ApiInterface) : ViewModel() {

    private val quotesDataRepository = QuotesDataRepository(apiService)

    // Observables for the Activity
    private val _quotesLiveData = MutableLiveData<QuoteData>()
    val quotesLiveData: LiveData<QuoteData> get() = _quotesLiveData

    private val _quotesLiveData2 = MutableLiveData<ApiResponse<QuoteData?>>()
    val quotesLiveData2: LiveData<ApiResponse<QuoteData?>> get() = _quotesLiveData2

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun fetchQuotes(page: Int = 1) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val data = quotesDataRepository.getQuotes(page)
                _quotesLiveData.value = data!!
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Unknown Error"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchQuotes2(page: Int = 1) {
        viewModelScope.launch {
            _quotesLiveData2.value = ApiResponse.ShowLoading()
            try {
                val data = quotesDataRepository.getQuotes2(page)
                _quotesLiveData2.value = data
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Unknown Error"
                _quotesLiveData2.value = ApiResponse.Error(e)
            } finally {
                _isLoading.value = false
                _quotesLiveData2.value = ApiResponse.HideLoading()
            }
        }
    }

}