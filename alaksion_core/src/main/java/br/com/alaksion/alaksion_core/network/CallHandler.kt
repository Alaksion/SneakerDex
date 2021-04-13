package br.com.alaksion.alaksion_core.network

import androidx.lifecycle.MutableLiveData
import br.com.alaksion.alaksion_core.network.networkerror.NetWorkErrorTypes
import br.com.alaksion.alaksion_core.network.networkerror.NetworkError
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Response
import java.lang.Exception
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class CallHandler<R : Any> {
    lateinit var client: Deferred<Response<R>>

    fun call(): MutableLiveData<Resource<R>> {
        val result = MutableLiveData<Resource<R>>()
        result.value = Resource.Loading()

        GlobalScope.launch {
            try {
                val response = client.await()
                withContext(Dispatchers.Main) {
                    result.value = handleResponse(response)
                }
            } catch (e: Exception) {
                result.value = Resource.Error(makeNetWorkException(e))
                e.printStackTrace()
            }
        }
        return result
    }

    private fun handleResponse(response: Response<R>): Resource<R> {
        if (response.isSuccessful) {
            return Resource.Success(response.body())
        } else {
            val errorData = NetworkError(
                errorType = NetWorkErrorTypes.FAILURE,
                code = response.code(),
                description = response.errorBody().toString()
            )
            return Resource.Error(errorData)
        }
    }

    private fun makeNetWorkException(exception: Exception): NetworkError {
        return when (exception) {
            is ConnectException, is UnknownHostException -> NetworkError(NetWorkErrorTypes.CONNECTION)
            is HttpException -> handleHttpException(exception)
            is SocketTimeoutException -> NetworkError(NetWorkErrorTypes.TIME_OUT)
            else -> NetworkError(NetWorkErrorTypes.FAILURE)
        }
    }

    private fun handleHttpException(e: HttpException): NetworkError {
        return NetworkError(
            errorType = NetWorkErrorTypes.FAILURE,
            code = e.code(),
            description = e.message()
        )
    }

}

fun <R : Any> networkCall(block: CallHandler<R>.() -> Unit): MutableLiveData<Resource<R>> =
    CallHandler<R>().apply(block).call()