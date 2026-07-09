package com.sachinshah.practical.mvi

sealed class LoginIntent {

    data class EmailChanged(val email: String): LoginIntent()

    data class PasswordChange(val password: String): LoginIntent()

    object LoginClicked : LoginIntent()
}