package com.demo.myapplication.retrofit

import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log

object RetrofitHelper {

    val BASE_URL = "http://api.quotable.io/"
    var retrofit: Retrofit? = null
    val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    fun initRetrofit(): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)

/*
        val originalRequest = chain.request()
        val builder = originalRequest.newBuilder()

        // Get the token from the DataStore using coroutines
        val barrierToken = runBlocking {
            getTokenFromDataStore()
        }

        val authorization = "Bearer ${preferencesHelper.getInstance().barrierToken}"
        //   val authorization = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vdGVhY2hlcnNwb29sLmFwcHVuby5jby9hcGkvVXNlci9Mb2dpbiIsImlhdCI6MTY5MTE1NjUwMywiZXhwIjoxNzIyNjkyNTAzLCJuYmYiOjE2OTExNTY1MDMsImp0aSI6ImlBZkl0U1RNUnUySnV3NkQiLCJzdWIiOiI1IiwicHJ2IjoiMjNiZDVjODk0OWY2MDBhZGIzOWU3MDFjNDAwODcyZGI3YTU5NzZmNyIsImlkIjo1fQ.0ebiWybvyy2rhg7OB9W2cwuHZ3MsgYwK2v8tVF0UPA4"
        Logger.error("Authorization", authorization)
        builder.header(BuildConfig.Authorization, authorization)
        builder.method(originalRequest.method, originalRequest.body)
        return chain.proceed(builder.build())*/

           /* .addInterceptor { chain ->

                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer <token>")
                    .build()
                chain.proceed(request)
            }*/
            .build()

        if (retrofit == null) {
            retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit!!

    }


}