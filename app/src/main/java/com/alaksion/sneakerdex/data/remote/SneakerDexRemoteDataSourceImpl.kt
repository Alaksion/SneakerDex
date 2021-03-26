package com.alaksion.sneakerdex.data.remote

import com.alaksion.sneakerdex.data.datasource.SneakerDexRemoteDataSource
import com.alaksion.sneakerdex.data.model.GetSneakersRequestParamsData
import com.alaksion.sneakerdex.data.model.SneakerListResponseData
import com.alaksion.sneakerdex.data.model.SneakerResponseData
import com.alaksion.sneakerdex.shared.network.Resource

class SneakerDexRemoteDataSourceImpl : SneakerDexRemoteDataSource {

    private val api = SneakersService.INSTANCE

    override suspend fun getSneakers(requestBodyData: GetSneakersRequestParamsData): Resource<SneakerListResponseData> {
        val result: Resource<SneakerListResponseData>

        val request = api.getSneakers(
            requestBodyData.limit,
            requestBodyData.page,
            requestBodyData.styleId,
            requestBodyData.name,
            requestBodyData.shoe,
            requestBodyData.brand,
            requestBodyData.gender,
            requestBodyData.colorway,
            requestBodyData.releaseDate,
            requestBodyData.releaseYear,
            requestBodyData.sort
        )

        result = if (request.isSuccessful) {
            Resource.Success(request.body())
        } else {
            Resource.Error(request.message())
        }
        return result
    }

    override suspend fun getSneaker(sneakerId: String): Resource<SneakerResponseData> {
        val result: Resource<SneakerResponseData>
        val request = api.getSneaker(sneakerId)

        result = if (request.isSuccessful) {
            Resource.Success(request.body())
        } else {
            Resource.Error(request.message())
        }

        return result
    }
}