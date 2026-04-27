package com.demo.myapplication.retrofit

import com.demo.myapplication.ApiResponse
import com.demo.myapplication.model.QuoteData
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {


    @GET("/quotes")
    suspend fun getQuotes(@Query("page") page: Int): Response<QuoteData>

  @GET("/quotes")
    suspend fun getQuotes2(@Query("page") page: Int): ApiResponse<QuoteData>


   /* @POST(EndPoint.COMMON_LOGIN)
    @FormUrlEncoded
    suspend fun commonLogin(
        @Field("mobile_number") mobileNumber: String,
        @Field("device_token_android") deviceTokenAndroid: String,
        @Field("device_id_android") deviceIDAndroid: String = EndPoint.DeviceInfo.DeviceId,
        @Field("device_type") deviceType: Int = 1) : BaseObjectResponse<AuthenticationModel>
*/
}