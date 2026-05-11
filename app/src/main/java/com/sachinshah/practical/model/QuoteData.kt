package com.sachinshah.practical.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuoteData(
    @SerializedName("count")
    val count: Int = 0,
    @SerializedName("lastItemIndex")
    val lastItemIndex: Int = 0,
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("results")
    val resultModels: ArrayList<ResultModel> = arrayListOf(),
    @SerializedName("totalCount")
    val totalCount: Int = 0,
    @SerializedName("totalPages")
    val totalPages: Int = 0
): Parcelable