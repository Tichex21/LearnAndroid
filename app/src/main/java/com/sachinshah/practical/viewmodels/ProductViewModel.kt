package com.sachinshah.practical.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sachinshah.practical.model.ProductModel
import com.sachinshah.practical.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(var repository: ProductRepository) : ViewModel() {

    val products: StateFlow<ArrayList<ProductModel>> = repository.getProducts
    val getSingleProduct: StateFlow<ProductModel> = repository.getProductById

    init {

        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            repository.getProducts()

        }
    }

    fun getProductById(id: Int) {
        viewModelScope.launch {
            repository.getProductById(id)

        }
    }
}