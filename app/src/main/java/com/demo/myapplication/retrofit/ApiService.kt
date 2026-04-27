package com.demo.myapplication.retrofit

import com.demo.myapplication.MyAppClass

class ApiService {

    var apiInterface : ApiInterface?=null

    fun initApiInterface() : ApiInterface = apiInterface ?: synchronized(this) {
        if(apiInterface == null){
            apiInterface = MyAppClass.retrofit.create(ApiInterface::class.java)
        }
        return@synchronized apiInterface!!
    }


}