package com.sachinshah.practical

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application(){

    companion object {
        private lateinit var instance: MyApplication
        fun getAppContext(): MyApplication {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}