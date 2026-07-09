package com.sachinshah.practical

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.red
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sachinshah.practical.model.QuoteModel
import com.sachinshah.practical.databinding.ActivityMainBinding
import com.sachinshah.practical.viewmodels.QuoteViewModelFactory
import com.sachinshah.practical.viewmodels.QuotesViewModel

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


       val name: String="Sachin"
        val letngrth= name.let {
            print(it)
            it.length
        }


        val student = Student().run {
            this.name="Sachin"
            this.age=50
            "${this.name} **  ${this.age}"

        }
        print(student)

        val studere= Student().apply {
            this.name="rew"
            this.age=24
        }

        val srte= Student().also {
            Log.e("rewrw","rew {$it}")
        }

        with(srte){
          this.name="re"
            this.age=50

        }

    }
    data class Student(var name: String="rwe",var age: Int=50){}

}