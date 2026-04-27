package com.demo.myapplication.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.myapplication.model.QuoteModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class QuotesViewModel(val context: Context) : ViewModel() {

    private var currentIndex = 0;
    private val _quotesLiveData = MutableLiveData<List<QuoteModel>>()
    private val quotesLiveData: LiveData<List<QuoteModel>> = _quotesLiveData
    private val _quote = MutableLiveData<QuoteModel>()
    val quote: LiveData<QuoteModel> = _quote


    fun getQuotes() {
        _quotesLiveData.value = loadJSONFromAsset(context)
        setQuote(quotesLiveData.value?.get(currentIndex) ?: QuoteModel("", "", ""))
    }

    fun loadJSONFromAsset(context: Context): ArrayList<QuoteModel> {
        val json = context.assets.open("quote_list.json").bufferedReader().use { it.readText() }

        val type = object : TypeToken<ArrayList<QuoteModel>>() {}.type
        return Gson().fromJson(json, type)
    }

    fun setQuote(quote: QuoteModel) {
        _quote.value = quote
    }

    fun nextQuote() {
        quotesLiveData.value?.let {
            if (currentIndex < it.size-1) {

                currentIndex++
                setQuote(it[currentIndex])
            }
        }
    }

    fun previousQuote() {
        quotesLiveData.value?.let {
            if (currentIndex > 0) {
                currentIndex--
                setQuote(it[currentIndex])
            }
        }
    }
}