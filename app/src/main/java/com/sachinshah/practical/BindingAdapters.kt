package com.sachinshah.practical

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil3.load
import coil3.request.CachePolicy
import coil3.request.crossfade

object BindingAdapters{

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun imageUrl(imgView: ImageView, imgUrl: String?) {
        imgUrl?.let {
            imgView.load(it) {
                crossfade(true)
                crossfade(100)
                diskCachePolicy(CachePolicy.ENABLED)
                memoryCachePolicy(CachePolicy.ENABLED)
                networkCachePolicy(CachePolicy.ENABLED)
            }
        }
    }


}