package com.alaksion.sneakerdex.domain.model

import com.alaksion.sneakerdex.data.model.SneakerData
import com.google.gson.annotations.SerializedName

data class SneakersResponse(
    val results: List<SneakerData>
)