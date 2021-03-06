package com.alaksion.sneakerdex.domain.usecase

import com.alaksion.sneakerdex.data.model.mapToDomain
import com.alaksion.sneakerdex.data.repository.SneakerRepositoryImpl
import com.alaksion.sneakerdex.domain.model.SneakersResponse
import com.alaksion.sneakerdex.core.network.Resource

class GetSneakerUseCase(
    private val repositoryImpl: SneakerRepositoryImpl
) {
    suspend operator fun invoke(sneakerId: String): Resource<SneakersResponse> {
        return repositoryImpl.getSneaker(sneakerId).resourceType {
            it?.mapToDomain()
        }
    }
}