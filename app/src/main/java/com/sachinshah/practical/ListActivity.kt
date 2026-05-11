package com.sachinshah.practical

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
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sachinshah.practical.adapter.QuotesAdapter
import com.sachinshah.practical.databinding.ActivityListBinding
import com.sachinshah.practical.model.LoginRequest
import com.sachinshah.practical.viewmodels.QuotesListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield

@AndroidEntryPoint
class ListActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityListBinding

    lateinit var quotesViewModel: QuotesListViewModel
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
        quotesViewModel = ViewModelProvider.create(this)[QuotesListViewModel::class.java]
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

       quotesViewModel.fetchQuotes(1)
      //  quotesViewModel.login(LoginRequest("mor_2314","83r5^_"))
        quotesViewModel.quotesLiveData.observe(this, Observer {

            when (it) {

                is ApiResponse.ShowLoading -> {
                    mBinding.progressBar.visibility = View.VISIBLE
                }

                is ApiResponse.Success -> {
                    it.data?.let { it ->
                        quotesAdapter.setData(it.resultModels)
                    }
                }

                is ApiResponse.Error -> {
                    Log.e("Error",it.error?.printStackTrace().toString())
                }

                is ApiResponse.HideLoading -> {
                    mBinding.progressBar.visibility = View.GONE
                }
            }

        })



        CoroutineScope(Dispatchers.IO).launch {
           track1()
        }

        CoroutineScope(Dispatchers.IO).launch {
            track2()
        }

        CoroutineScope(Dispatchers.IO).launch {
            cancellabeCoruotine()
        }

        lifecycleScope.launch {
            Log.e("lifecycleScope","lifecycleScope")
            printFlower2()

        }
        A()


    }



    suspend fun track1() {
        Log.e("track1 ", " Happen 1")
        yield()
        Log.e("track1 ", " Happen 2")
    }

    suspend fun track2() {
        Log.e("track2 ", " Happen 1")
        yield()
        Log.e("track2 ", " Happen 2")
    }

    suspend fun printFlower() {
        var instaCnt = 0
        var fbCnt = 0
        val job = CoroutineScope(Dispatchers.IO).launch {
            instaCnt = getFBFollower()
        }

        val job1 = CoroutineScope(Dispatchers.IO).launch {
            fbCnt = getInstaFollower()
        }
        job.join()
        job1.join()
        Log.e("FbFollower ", "${fbCnt}")
        Log.e("InstaFollower ", "${instaCnt}")
    }

    open fun A(){
        Log.e("FbFollower ", "iho")
        Log.e("InstaFollower ", "oii")
    }

    suspend fun printFlower2() {
        val job = CoroutineScope(Dispatchers.IO).async {
         getFBFollower()
        }

        val job1 = CoroutineScope(Dispatchers.IO).async {
            getInstaFollower()
        }
        Log.e("FbFollower  await ", "${job.await()}")
        Log.e("InstaFollower await ", "${job1.await()}")
    }


    suspend fun pritFoloower3(){
        CoroutineScope(Dispatchers.IO).launch{
            var fb= async { getFBFollower() }
            var insta= async { getInstaFollower() }
            Log.e("FbFollower  await ", "${fb.await()}")
            Log.e("InstaFollower await ", "${insta.await()}")
        }
    }

    suspend fun getFBFollower(): Int {

        delay(5000)

        return 100

    }

    suspend fun getInstaFollower(): Int {

        delay(500)

        return 100

    }


    suspend fun cancellabeCoruotine(){

        val jobParent= CoroutineScope(Dispatchers.IO).launch {

            for (i in 1..1000){
                if(isActive)
                {
                    executeLongRunningTask()
                    Log.e("TAG", "executeLongRunningTask: $i")
                }

            }
        }
        delay(200)
        Log.e("Parent Cancelled","Job Parent Cancelling")
        jobParent.cancel()
        jobParent.join()
        Log.e("Parent Cancelled","Job Parent Completed")

    }

    fun executeLongRunningTask(){
        var j=0
        for(i in 1..100000000){

            for (i in j until 100000000){
                j= i+1000
            }
        }
    }



}