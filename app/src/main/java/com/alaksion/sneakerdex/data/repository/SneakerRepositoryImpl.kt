package com.alaksion.sneakerdex.data.repository

import androidx.lifecycle.LiveData
import com.alaksion.sneakerdex.data.model.SneakerListResponseData
import com.alaksion.sneakerdex.data.model.SneakerResponseData
import com.alaksion.sneakerdex.data.model.GetSneakersRequestParams
import com.alaksion.sneakerdex.data.remote.SneakerDexRemoteDataSourceImpl
import com.alaksion.sneakerdex.domain.model.SneakerListResponse
import com.alaksion.sneakerdex.domain.repository.SneakersRepository
import com.alaksion.sneakerdex.shared.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class SneakerRepositoryImpl(
    private val dataSource: SneakerDexRemoteDataSourceImpl
) : SneakersRepository {

    override suspend fun getSneakers(requestParams: GetSneakersRequestParams): LiveData<Resource<SneakerListResponseData>> {
        return withContext(Dispatchers.IO) {
            dataSource.getSneakers(requestParams)
        }
    }

    override suspend fun getSneaker(sneakerId: String): LiveData<Resource<SneakerResponseData>> {
        return withContext(Dispatchers.IO) {
            dataSource.getSneaker(sneakerId)
        }
    }
}