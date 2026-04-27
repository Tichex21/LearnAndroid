package com.demo.myapplication

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
import com.demo.myapplication.viewmodels.QuoteViewModelFactory
import com.demo.myapplication.viewmodels.QuotesViewModel

class MainActivity : AppCompatActivity() {

    lateinit var mBinding : ActivityMainBinding
    lateinit var quotesViewModel: QuotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        quotesViewModel = ViewModelProvider(this, QuoteViewModelFactory(application)).get(QuotesViewModel::class.java)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(
            R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        quotesViewModel.getQuotes()

        quotesViewModel.quote.observe(this, Observer<QuoteModel>{
            mBinding.quoteModel = it
            Log.e("url",it.image)
            BindingAdapters.imageUrl(mBinding.ivBackgroundImage,it.image)
        })

        mBinding.tvNext.setOnClickListener {
            quotesViewModel.nextQuote()
        }
        mBinding.tvPrevious.setOnClickListener {
            quotesViewModel.previousQuote()
        }

    }

}