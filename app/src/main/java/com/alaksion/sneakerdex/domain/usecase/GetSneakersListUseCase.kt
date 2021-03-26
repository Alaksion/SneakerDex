package com.alaksion.sneakerdex.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.alaksion.sneakerdex.data.repository.SneakerRepositoryImpl
import com.alaksion.sneakerdex.data.model.mapToData
import com.alaksion.sneakerdex.data.model.mapToDomain
import com.alaksion.sneakerdex.domain.model.GetSneakersRequestParams
import com.alaksion.sneakerdex.domain.model.SneakerListResponse
import com.alaksion.sneakerdex.shared.network.Resource

class GetSneakersListUseCase(
    private val repositoryImpl: SneakerRepositoryImpl
) {

    suspend operator fun invoke(requestParams: GetSneakersRequestParams): LiveData<Resource<SneakerListResponse>> {
        return Transformations.map(repositoryImpl.getSneakers(requestParams.mapToData())) { resource ->
            resource.resourceType {
                it?.mapToDomain()
            }
        }
    }
}