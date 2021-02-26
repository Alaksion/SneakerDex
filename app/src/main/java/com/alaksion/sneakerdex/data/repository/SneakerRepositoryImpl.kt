package com.alaksion.sneakerdex.data.repository

import com.alaksion.sneakerdex.data.model.SneakerData
import com.alaksion.sneakerdex.data.model.SneakerListResponseData
import com.alaksion.sneakerdex.data.model.SneakerResponseData
import com.alaksion.sneakerdex.data.remote.SneakersService
import com.alaksion.sneakerdex.data.repository.request.GetSneakersRequestParams
import com.alaksion.sneakerdex.domain.repository.SneakersRepository
import com.alaksion.sneakerdex.shared.RetrofitClient
import com.alaksion.sneakerdex.shared.listeners.ApiListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SneakerRepositoryImpl : SneakersRepository {

    private val retrofitClient = RetrofitClient.createService(SneakersService::class.java)

    override fun getSneakers(
        apiListener: ApiListener<SneakerListResponseData>,
        requestParams: GetSneakersRequestParams
    ) {
        val call: Call<SneakerListResponseData> = retrofitClient.getSneakers(
            requestParams.limit,
            requestParams.page,
            requestParams.styleId,
            requestParams.name,
            requestParams.shoe,
            requestParams.brand,
            requestParams.gender,
            requestParams.colorway,
            requestParams.releaseDate,
            requestParams.releaseYear,
            requestParams.sort
        )

        callApi(call, apiListener)
    }

    override fun getSneaker(apiListener: ApiListener<SneakerResponseData>, sneakerId: String) {
        val call : Call<SneakerResponseData> = retrofitClient.getSneaker(sneakerId)
        callApi(call, apiListener)
    }

    private fun <T> callApi(call : Call<T>, apiListener: ApiListener<T>) {
        call.enqueue(object : Callback<T>{

            override fun onFailure(call: Call<T>, t: Throwable) {
                apiListener.onError("Ocorreu um erro inesperado")
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    response.body()?.let { apiListener.onSuccess(it) }
                } else {
                    apiListener.onError(response.errorBody().toString())
                }
            }
        })
    }
}