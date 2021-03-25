package com.alaksion.sneakerdex.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alaksion.sneakerdex.data.datasource.SneakerDexRemoteDataSource
import com.alaksion.sneakerdex.data.model.GetSneakersRequestParams
import com.alaksion.sneakerdex.data.model.SneakerListResponseData
import com.alaksion.sneakerdex.data.model.SneakerResponseData
import com.alaksion.sneakerdex.shared.network.Resource
import retrofit2.Response

class SneakerDexRemoteDataSourceImpl : SneakerDexRemoteDataSource {

    private val api = SneakersService.INSTANCE

    override suspend fun getSneakers(requestBody: GetSneakersRequestParams): LiveData<Resource<SneakerListResponseData>> {
        val result = MutableLiveData<Resource<SneakerListResponseData>>()

        val request = api.getSneakers(
            requestBody.limit,
            requestBody.page,
            requestBody.styleId,
            requestBody.name,
            requestBody.shoe,
            requestBody.brand,
            requestBody.gender,
            requestBody.colorway,
            requestBody.releaseDate,
            requestBody.releaseYear,
            requestBody.sort
        )
        if (request.isSuccessful) {
            result.value = Resource.Success(request.body()?.value!!)
        } else {
            result.value = Resource.Error(request.message())
        }
        return result
    }

    override suspend fun getSneaker(sneakerId: String): LiveData<Resource<SneakerResponseData>> {
        val result = MutableLiveData<Resource<SneakerResponseData>>()
        val request = api.getSneaker(sneakerId)

        if (request.isSuccessful) {
            result.value = Resource.Success(request.body()?.value!!)
        } else {
            result.value = Resource.Error(request.message())
        }

        return result
    }
}