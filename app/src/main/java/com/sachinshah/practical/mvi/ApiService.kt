package com.sachinshah.practical.mvi

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {


    @POST("login")
    @FormUrlEncoded
    suspend fun login(
        @Field("email")    email: String,
        @Field("password") password: String
    ) : Response<LoginResponse>
}