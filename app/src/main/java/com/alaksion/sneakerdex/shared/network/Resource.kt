package com.alaksion.sneakerdex.shared.network

sealed class Resource<out T>(val data: T? = null) {

    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(val errorMsg: String) : Resource<T>()

    fun <R> resourceType(onSuccess: (T?) -> R?): Resource<R> = when (this) {
        is Success -> Success(onSuccess(data))
        is Error -> Error(errorMsg)

    }
}