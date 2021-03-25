package com.alaksion.sneakerdex.data.model

import com.alaksion.sneakerdex.domain.model.SneakersResponse
import com.google.gson.annotations.SerializedName

data class SneakerResponseData(
    @SerializedName("results")
    val results : List<SneakerData>
)

fun SneakerResponseData.mapToDomain() = SneakersResponse(
    results = this.results
)