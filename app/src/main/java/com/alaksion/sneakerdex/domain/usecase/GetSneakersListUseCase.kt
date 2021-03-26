package com.alaksion.sneakerdex.domain.usecase

import com.alaksion.sneakerdex.data.repository.SneakerRepositoryImpl
import com.alaksion.sneakerdex.data.model.mapToData
import com.alaksion.sneakerdex.data.model.mapToDomain
import com.alaksion.sneakerdex.domain.model.GetSneakersRequestParams
import com.alaksion.sneakerdex.domain.model.SneakerListResponse
import com.alaksion.sneakerdex.shared.network.Resource

class GetSneakersListUseCase(
    private val repositoryImpl: SneakerRepositoryImpl
) {

    suspend operator fun invoke(requestParams: GetSneakersRequestParams): Resource<SneakerListResponse> {
        return repositoryImpl.getSneakers(requestParams.mapToData()).resourceType {
            it?.mapToDomain()
        }
    }
}
