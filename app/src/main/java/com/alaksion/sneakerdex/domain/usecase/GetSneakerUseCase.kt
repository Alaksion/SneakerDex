package com.alaksion.sneakerdex.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.alaksion.sneakerdex.data.model.mapToDomain
import com.alaksion.sneakerdex.data.repository.SneakerRepositoryImpl
import com.alaksion.sneakerdex.domain.model.SneakersResponse
import com.alaksion.sneakerdex.shared.network.Resource

class GetSneakerUseCase(
    private val repositoryImpl: SneakerRepositoryImpl
) {
    suspend operator fun invoke(sneakerId: String): LiveData<Resource<SneakersResponse>> {
        return Transformations.map(repositoryImpl.getSneaker(sneakerId)) { resource ->
            resource.resourceType {
                it?.mapToDomain()
            }
        }
    }
}