package com.sachinshah.practical.mvi

data class LoginState (
    var email: String="",
    var password: String="",
    var isLoading: Boolean=false,
    var isLoggedIn: Boolean=false,
    var loginResponse: LoginResponse?=null,
    var isError: String?=null
)