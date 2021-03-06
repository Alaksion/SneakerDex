package com.alaksion.sneakerdex.data.repository

import com.alaksion.sneakerdex.data.model.SneakerListResponseData
import com.alaksion.sneakerdex.data.model.SneakerResponseData
import com.alaksion.sneakerdex.data.model.GetSneakersRequestParamsData
import com.alaksion.sneakerdex.data.remote.SneakerDexRemoteDataSourceImpl
import com.alaksion.sneakerdex.domain.repository.SneakersRepository
import com.alaksion.sneakerdex.core.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SneakerRepositoryImpl(
    private val dataSource: SneakerDexRemoteDataSourceImpl
) : SneakersRepository {

    override suspend fun getSneakers(requestParamsData: GetSneakersRequestParamsData): Resource<SneakerListResponseData> {

        return withContext(Dispatchers.IO) {
            dataSource.getSneakers(requestParamsData)
        }
    }

    override suspend fun getSneaker(sneakerId: String): Resource<SneakerResponseData> {

        return withContext(Dispatchers.IO) {
            dataSource.getSneaker(sneakerId)
        }
    }
}