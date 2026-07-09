package com.sachinshah.practical.mvi

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(
    val userId: String,
    val token : String
): Parcelable
