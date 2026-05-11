package com.sachinshah.practical.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(
    val token: String
): Parcelable