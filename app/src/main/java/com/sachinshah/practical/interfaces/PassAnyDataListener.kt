package com.sachinshah.practical.interfaces

interface PassAnyDataListener<T> {

    fun showProgress()
    fun passData(t: T)
    fun passError(t: Throwable)
    fun hideProgress()
}