package com.alaksion.sneakerdex.shared.listeners

interface ApiListener<T> {

    fun onSuccess(response: T)
    fun onError(errorMessage: String)
}