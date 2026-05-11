package com.sachinshah.practical

sealed class ApiResponse<T>(val data: T? = null, val error: Throwable? = null) {

    class ShowLoading<T> : ApiResponse<T>()

    class Success<T>(data: T) : ApiResponse<T>(data)

    class Error<T>(error: Throwable) : ApiResponse<T>(null, error)

    class HideLoading<T> : ApiResponse<T>()

}