package com.alaksion.sneakerdex.shared.network

sealed class Resource<out T>(val data: T? = null) {

    class Success<T>(data: T): Resource<T>(data)
    class Error<T>(errorMsg: String): Resource<T>()
}