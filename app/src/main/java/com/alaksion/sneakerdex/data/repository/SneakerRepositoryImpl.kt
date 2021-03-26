package com.alaksion.sneakerdex.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alaksion.sneakerdex.data.model.SneakerListResponseData
import com.alaksion.sneakerdex.data.model.SneakerResponseData
import com.alaksion.sneakerdex.data.model.GetSneakersRequestParamsData
import com.alaksion.sneakerdex.data.remote.SneakerDexRemoteDataSourceImpl
import com.alaksion.sneakerdex.domain.repository.SneakersRepository
import com.alaksion.sneakerdex.shared.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SneakerRepositoryImpl(
    private val dataSource: SneakerDexRemoteDataSourceImpl
) : SneakersRepository {

    override suspend fun getSneakers(requestParamsData: GetSneakersRequestParamsData): LiveData<Resource<SneakerListResponseData>> {
        val result = MutableLiveData<Resource<SneakerListResponseData>>()

        withContext(Dispatchers.IO) {
            val data = dataSource.getSneakers(requestParamsData)

            withContext(Dispatchers.Main) {
                result.value = data
            }
        }

        return result

    }

    override suspend fun getSneaker(sneakerId: String): LiveData<Resource<SneakerResponseData>> {
        val result = MutableLiveData<Resource<SneakerResponseData>>()

        withContext(Dispatchers.IO) {
            val data = dataSource.getSneaker(sneakerId)

            withContext(Dispatchers.Main) {
                result.value = data
            }
        }

        return result
    }
}