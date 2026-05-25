package com.sachinshah.practical.repository

import com.sachinshah.practical.model.ProductModel
import com.sachinshah.practical.retrofit.ApiCall
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject


class ProductRepository @Inject  constructor(var apiCall: ApiCall)  {

    private val _getProducts= MutableStateFlow<ArrayList<ProductModel>>(arrayListOf())
    val getProducts : StateFlow<ArrayList<ProductModel>> =_getProducts

 private val _getProductById= MutableStateFlow<ProductModel>(ProductModel())
    val getProductById : StateFlow<ProductModel> =_getProductById


    suspend fun getProducts(){

        val response=apiCall.getProducts()
        if(response.isSuccessful && response.body()!=null){
            response.body()?.let {
                _getProducts.emit(it    )
            }
        }
    }

    suspend fun getProductById(id: Int){

        val response=apiCall.getProductById(id)
        if(response.isSuccessful && response.body()!=null){
            response.body()?.let {
                _getProductById.emit(it    )
            }
        }
    }
}