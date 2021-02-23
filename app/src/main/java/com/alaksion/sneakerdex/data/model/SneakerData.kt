package com.alaksion.sneakerdex.data.model

import com.google.gson.annotations.SerializedName

data class SneakerData(
    @SerializedName(value = "id")
    val id: String,

    @SerializedName("brand")
    val brand: String,

    @SerializedName(value = "colorway")
    val colorway: String,

    @SerializedName(value = "gender")
    val gender: String,

    @SerializedName(value = "media")
    val media: SneakerMediaData,

    @SerializedName(value = "releaseDate")
    val releaseDate: String,

    @SerializedName(value = "retailPrice")
    val retailPrice: String,

    @SerializedName(value = "styleId")
    val styleId: String,

    @SerializedName(value = "shoe")
    val shoe: String,

    @SerializedName(value = "name")
    val name: String,

    @SerializedName(value = "title")
    val title: String,

    @SerializedName(value = "year")
    val year: Int
)