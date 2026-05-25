package com.sachinshah.practical.retrofit

import com.sachinshah.practical.model.LoginRequest
import com.sachinshah.practical.model.QuoteData
import com.google.gson.JsonObject
import com.sachinshah.practical.model.ProductModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiCall {


    @GET("/quotes")
    suspend fun getQuotes(@Query("page") page: Int): Response<QuoteData>


    @POST("auth/login")
    suspend fun login(
        @Body request   : LoginRequest
    ): Response<JsonObject>

    @GET("/products")
    suspend fun getProducts() : Response<ArrayList<ProductModel>>

    @GET("/products/{id}")
    suspend fun getProductById(id: Int) : Response<ProductModel>
    /* @POST(EndPoint.COMMON_LOGIN)
     @FormUrlEncoded
     suspend fun commonLogin(
         @Field("mobile_number") mobileNumber: String,
         @Field("device_token_android") deviceTokenAndroid: String,
         @Field("device_id_android") deviceIDAndroid: String = EndPoint.DeviceInfo.DeviceId,
         @Field("device_type") deviceType: Int = 1) : BaseObjectResponse<AuthenticationModel>
 */
}

