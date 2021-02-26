package com.alaksion.sneakerdex.data.model

import com.google.gson.annotations.SerializedName

data class SneakerResponseData(
    @SerializedName("results")
    val results : List<SneakerData>
)