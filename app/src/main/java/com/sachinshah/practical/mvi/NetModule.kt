package com.sachinshah.practical.mvi

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetModule {


    @Provides
    @Singleton
    @Ketro
    fun providesApiServices(retrofit: Retrofit): ApiService{
        return  retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    @Nero
    fun provideApi(retrofit: Retrofit) : ApiService{
        return  retrofit.create(ApiService::class.java)
    }
}