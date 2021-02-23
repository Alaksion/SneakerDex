package com.alaksion.sneakerdex.domain.repository

import com.alaksion.sneakerdex.data.model.SneakerData
import com.alaksion.sneakerdex.data.model.SneakerResponseData
import com.alaksion.sneakerdex.data.repository.request.GetSneakersRequestParams
import com.alaksion.sneakerdex.shared.listeners.ApiListener

interface SneakersRepository {
    fun getSneakers(apiListener: ApiListener<SneakerResponseData>, requestParams: GetSneakersRequestParams)
}