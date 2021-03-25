package com.alaksion.sneakerdex.domain.model

import com.alaksion.sneakerdex.data.model.SneakerData

data class SneakerListResponse(
    val count: Int,
    val results: List<SneakerData>
)