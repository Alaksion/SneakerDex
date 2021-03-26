package com.alaksion.sneakerdex.domain.model

data class SneakerListResponse(
    val count: Int,
    val results: List<Sneaker>
)