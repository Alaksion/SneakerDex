package br.com.alaksion.alaksion_core.network.networkerror

import java.io.Serializable

data class NetworkError(
    val errorType: NetWorkErrorTypes,
    val code: Int? = null,
    val description: String? = null
) : Serializable


enum class NetWorkErrorTypes {
    TIME_OUT,
    FAILURE,
    CONNECTION
}