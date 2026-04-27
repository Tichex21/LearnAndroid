package com.demo.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.myapplication.adapter.QuotesAdapter
import com.demo.myapplication.databinding.ActivityListBinding
import com.demo.myapplication.model.QuoteModel
import com.demo.myapplication.databinding.ActivityMainBinding
import com.demo.myapplication.interfaces.PassAnyDataListener
import com.demo.myapplication.model.QuoteData
import com.demo.myapplication.retrofit.ApiService
import com.demo.myapplication.viewmodels.QuoteDataViewModelFactory
import com.demo.myapplication.viewmodels.QuoteViewModelFactory
import com.demo.myapplication.viewmodels.QuotesListViewModel
import com.demo.myapplication.viewmodels.QuotesViewModel

class ListActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityListBinding
    lateinit var quotesViewModel: QuotesListViewModel
    var apiService = ApiService().initApiInterface()
    lateinit var quotesAdapter: QuotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        quotesViewModel = ViewModelProvider.create(
            this,
            QuoteDataViewModelFactory(apiService)
        )[QuotesListViewModel::class.java]
        quotesAdapter = QuotesAdapter()
        with(mBinding.recyclerView) {
            layoutManager =
                LinearLayoutManager(this@ListActivity, LinearLayoutManager.VERTICAL, false)
            adapter = quotesAdapter
        }

        /* quotesViewModel.quotesLiveData.observe(this) { data ->
             quotesAdapter.setData(data.resultModels)
         }

         quotesViewModel.isLoading.observe(this) { show ->
             mBinding.progressBar.visibility = if(show) View.VISIBLE else View.GONE
         }*/

        //quotesViewModel.fetchQuotes(1)
        quotesViewModel.fetchQuotes2(1)
        quotesViewModel.quotesLiveData2.observe(this, Observer {

            when (it) {

                is ApiResponse.ShowLoading -> {
                    mBinding.progressBar.visibility =View.VISIBLE
                }

                is ApiResponse.Success -> {
                    it.data?.let {
                        quotesAdapter.setData(it.resultModels)
                    }
                }

                is ApiResponse.Error -> {}

                is ApiResponse.HideLoading -> {
                    mBinding.progressBar.visibility =View.GONE
                }
            }

        })


    }

}