package com.alaksion.sneakerdex.data.model

import com.google.gson.annotations.SerializedName

data class SneakerMediaData(
    @SerializedName(value = "imageUrl")
    val imageUrl : String,

    @SerializedName(value = "smallImageUrl")
    val smallImageUrl : String,

    @SerializedName(value = "thumbUrl")
    val thumbUrl : String
) {
}