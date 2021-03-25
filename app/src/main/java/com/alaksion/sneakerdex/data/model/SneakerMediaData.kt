package com.alaksion.sneakerdex.data.model

import com.alaksion.sneakerdex.domain.model.SneakerMedia
import com.google.gson.annotations.SerializedName

data class SneakerMediaData(
    @SerializedName(value = "imageUrl")
    val imageUrl : String?,

    @SerializedName(value = "smallImageUrl")
    val smallImageUrl : String?,

    @SerializedName(value = "thumbUrl")
    val thumbUrl : String?
)

fun SneakerMediaData.mapToDomain() = SneakerMedia(
    imageUrl = this.imageUrl,
    smallImageUrl = this.smallImageUrl,
    thumbUrl = this.thumbUrl
)