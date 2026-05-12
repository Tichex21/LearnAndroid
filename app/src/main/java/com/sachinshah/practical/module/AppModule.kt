package com.sachinshah.practical.module

import android.content.Context
import com.sachinshah.practical.MyApplication
import com.sachinshah.practical.prefdatastore.PrefDatastoreUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideAppContext(): MyApplication {
        return MyApplication.getAppContext()
    }

    @Provides
    @Singleton
    fun provideAPrefDatastore(@ApplicationContext context: Context): PrefDatastoreUtils {
        return PrefDatastoreUtils(context)
    }


}