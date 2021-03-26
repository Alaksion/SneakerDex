package com.alaksion.sneakerdex.data.model

import com.alaksion.sneakerdex.domain.model.SneakerListResponse
import com.google.gson.annotations.SerializedName

data class SneakerListResponseData(
    @SerializedName(value = "count")
    val count : Int,

    @SerializedName(value = "results")
    val results : List<SneakerData>
)

fun SneakerListResponseData.mapToDomain() = SneakerListResponse(
    count = this.count,
    results = this.results.map { item -> item.mapToDomain() }
)