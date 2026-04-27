package com.demo.myapplication.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuoteModel(
    @SerializedName("quote") var quote: String,
    @SerializedName("author") var author: String,
    @SerializedName("image") var image: String
) : Parcelable