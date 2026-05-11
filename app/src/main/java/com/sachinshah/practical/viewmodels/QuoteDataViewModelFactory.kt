package com.sachinshah.practical.viewmodels

import androidx.lifecycle.ViewModelProvider
import com.sachinshah.practical.retrofit.ApiCall

class QuoteDataViewModelFactory(private val apiService: ApiCall) : ViewModelProvider.Factory {

    /*override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QuotesListViewModel(apiService) as T
    }*/
}