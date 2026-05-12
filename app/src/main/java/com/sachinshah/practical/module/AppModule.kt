package com.sachinshah.practical.module

import com.sachinshah.practical.MyApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideAppContext(): MyApplication   {
        return MyApplication.getAppContext()
    }


}