package com.demo.myapplication.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.myapplication.retrofit.ApiInterface
import com.demo.myapplication.retrofit.ApiService

class QuoteDataViewModelFactory(private val apiService: ApiInterface) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QuotesListViewModel(apiService) as T
    }
}