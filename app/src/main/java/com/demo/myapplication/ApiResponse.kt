package com.demo.myapplication

sealed class ApiResponse<T>(val data: T? = null, val error: Throwable? = null) {

    class Success<T>(data: T) : ApiResponse<T>(data)
    class Error<T>(error: Throwable) : ApiResponse<T>(null, error)
    class ShowLoading<T> : ApiResponse<T>()
    class HideLoading<T> : ApiResponse<T>()

}