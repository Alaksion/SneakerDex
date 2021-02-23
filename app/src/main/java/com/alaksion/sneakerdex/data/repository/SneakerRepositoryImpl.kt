package com.alaksion.sneakerdex.data.repository

import com.alaksion.sneakerdex.data.model.SneakerData
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
        apiListener: ApiListener<SneakerResponseData>,
        requestParams: GetSneakersRequestParams
    ) {
        val call: Call<SneakerResponseData> = retrofitClient.getSneakers(
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

        call.enqueue(object : Callback<SneakerResponseData> {

            override fun onFailure(call: Call<SneakerResponseData>, t: Throwable) {
                apiListener.onError("Ocorreu um erro inesperado")
            }

            override fun onResponse(
                call: Call<SneakerResponseData>,
                response: Response<SneakerResponseData>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { apiListener.onSuccess(it) }
                } else {
                    apiListener.onError(response.errorBody().toString())
                }
            }
        })
    }
}