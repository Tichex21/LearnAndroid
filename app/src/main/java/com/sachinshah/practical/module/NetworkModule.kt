package com.sachinshah.practical.module

import com.sachinshah.practical.retrofit.ApiCall
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    val BASE_URL = "https://fakestoreapi.com/"

    @Provides
    @Singleton
    fun provideRetrofit(client :OkHttpClient.Builder): Retrofit {
       return Retrofit.Builder().baseUrl(BASE_URL)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesOKHttp(logging: HttpLoggingInterceptor): OkHttpClient.Builder {
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
        return client
    }

    @Provides
    @Singleton
    fun providesInterceptor() : HttpLoggingInterceptor {
       return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideApiInterface(retrofit: Retrofit): ApiCall {
        return retrofit.create(ApiCall::class.java)
    }

}