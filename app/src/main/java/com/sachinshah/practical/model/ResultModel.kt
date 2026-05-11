package com.sachinshah.practical.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultModel(
    @SerializedName("author")
    val author: String = "",
    @SerializedName("authorSlug")
    val authorSlug: String = "",
    @SerializedName("content")
    var content: String = "",
    @SerializedName("dateAdded")
    val dateAdded: String = "",
    @SerializedName("dateModified")
    val dateModified: String = "",
    @SerializedName("_id")
    val id: String = "",
    @SerializedName("length")
    val length: Int = 0,
    @SerializedName("tags")
    val tags: ArrayList<String> = arrayListOf()
): Parcelable