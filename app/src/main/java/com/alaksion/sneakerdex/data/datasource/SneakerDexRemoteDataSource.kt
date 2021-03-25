package com.alaksion.sneakerdex.data.datasource

import androidx.lifecycle.LiveData
import com.alaksion.sneakerdex.data.model.GetSneakersRequestParams
import com.alaksion.sneakerdex.data.model.SneakerListResponseData
import com.alaksion.sneakerdex.data.model.SneakerResponseData
import com.alaksion.sneakerdex.shared.network.Resource
import retrofit2.Response

interface SneakerDexRemoteDataSource {

    suspend fun getSneakers(requestBody: GetSneakersRequestParams): LiveData<Resource<SneakerListResponseData>>
    suspend fun getSneaker(sneakerId: String): LiveData<Resource<SneakerResponseData>>
}