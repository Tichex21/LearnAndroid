package com.demo.myapplication

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.demo.myapplication.model.QuoteModel
import com.demo.myapplication.databinding.ActivityMainBinding
import com.demo.myapplication.retrofit.ApiService
import com.demo.myapplication.retrofit.RetrofitHelper
import com.demo.myapplication.viewmodels.QuoteViewModelFactory
import com.demo.myapplication.viewmodels.QuotesViewModel

class MyAppClass : Application() {

    companion object {
        lateinit var instance: MyAppClass
            private set
        var retrofit = RetrofitHelper.initRetrofit()
    }

}