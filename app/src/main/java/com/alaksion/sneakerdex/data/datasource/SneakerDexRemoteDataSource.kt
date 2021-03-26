package com.alaksion.sneakerdex.data.datasource

import androidx.lifecycle.LiveData
import com.alaksion.sneakerdex.data.model.GetSneakersRequestParamsData
import com.alaksion.sneakerdex.data.model.SneakerListResponseData
import com.alaksion.sneakerdex.data.model.SneakerResponseData
import com.alaksion.sneakerdex.shared.network.Resource

interface SneakerDexRemoteDataSource {

    suspend fun getSneakers(requestBodyData: GetSneakersRequestParamsData): Resource<SneakerListResponseData>
    suspend fun getSneaker(sneakerId: String): Resource<SneakerResponseData>
}