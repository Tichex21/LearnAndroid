package com.sachinshah.practical

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.sachinshah.practical.adapter.QuotesAdapter2
import com.sachinshah.practical.databinding.ActivityListBinding
import com.sachinshah.practical.model.ResultModel
import com.sachinshah.practical.viewmodels.QuotesListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListActivity2 : AppCompatActivity() {

    lateinit var mBinding: ActivityListBinding

    val quotesViewModel: QuotesListViewModel by viewModels()
    lateinit var quotesAdapter: QuotesAdapter2

    val observ by lazy{
        Observ()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        lifecycle.addObserver(observ)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        quotesAdapter = QuotesAdapter2()
        with(mBinding.recyclerView) {
            layoutManager =
                LinearLayoutManager(this@ListActivity2, LinearLayoutManager.VERTICAL, false)
            adapter = quotesAdapter
        }
        quotesAdapter.submitList(generateList())
        mBinding.progressBar.visibility = View.GONE

        contiFunLoop()
        Handler(Looper.getMainLooper()).postDelayed({
            quotesAdapter.submitList(generateList2())
        }, 5000)
        Handler(Looper.getMainLooper()).postDelayed({
            generateList2(true)
        }, 10000)
    }

    fun generateList(): ArrayList<ResultModel> {

        val list = ArrayList<ResultModel>()

        for (i in 1..50) {
            val resultModel = ResultModel(
                id = i.toString(), content = "Author bros: ${i}", authorSlug = "ronak",
            )
            list.add(resultModel)
        }

        return list
    }


    fun contiFunLoop() {
        // indexed for loop
        for ((index, model) in quotesAdapter.currentList.withIndex()) {
            val resultModel: ResultModel = model
            Log.e("idx $index", resultModel.toString())
            if (index == 5)
                break
        }


        for (idx in quotesAdapter.currentList.indices) {
            Log.e("ibdx ${idx}", quotesAdapter.currentList[idx].toString())
            if (idx == 20)
                break
            else continue

        }

        quotesAdapter.currentList.forEachIndexed { idx, model ->
            if (idx == 5) {
                return@contiFunLoop
            }
            Log.e("indx ${idx}", model.toString())
            Log.e("indx ${idx}", myLab("Ronak", "Jain"))

        }

    }

    fun generateList2(forDelete: Boolean = false): ArrayList<ResultModel> {
        val newList = ArrayList(quotesAdapter.currentList)
        if (!forDelete) {
            newList[14] = newList[14].copy(id = "15", content = "Ronak")
            newList[29] = newList[29].copy(id = "30", content = "Ronak")
            newList[49] = newList[49].copy(id = "50", content = "Ronak")
            quotesAdapter.submitList(newList)
        } else {
            val idsToRemove = setOf("15", "30", "50")
            val updatedList = newList.filterNot { it.id in idsToRemove }
            quotesAdapter.submitList(updatedList)
        }
        return newList

    }


    val myLab= { name: String,lastName: String -> "My name is $name $lastName" }

}