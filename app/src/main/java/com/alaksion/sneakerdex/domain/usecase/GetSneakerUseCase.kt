package com.alaksion.sneakerdex.domain.usecase

import com.alaksion.sneakerdex.data.model.SneakerData
import com.alaksion.sneakerdex.data.model.SneakerResponseData
import com.alaksion.sneakerdex.data.repository.SneakerRepositoryImpl
import com.alaksion.sneakerdex.shared.listeners.ApiListener

class GetSneakerUseCase {

    private val repository = SneakerRepositoryImpl()

    fun execute (
        apiListener: ApiListener<SneakerResponseData>,
        sneakerId: String
    ) {
        repository.getSneaker(apiListener, sneakerId)
    }
}