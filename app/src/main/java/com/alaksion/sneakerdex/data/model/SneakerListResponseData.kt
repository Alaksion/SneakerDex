package com.alaksion.sneakerdex.data.model

import com.google.gson.annotations.SerializedName

data class SneakerListResponseData(
    @SerializedName(value = "count")
    val count : Int,

    @SerializedName(value = "results")
    val results : List<SneakerData>
)