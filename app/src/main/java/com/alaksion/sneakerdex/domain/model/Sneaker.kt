package com.alaksion.sneakerdex.domain.model

data class Sneaker(
    val id: String,
    val brand: String,
    val colorway: String,
    val gender: String,
    val media: SneakerMedia,
    val releaseDate: String,
    val retailPrice: String,
    val styleId: String,
    val shoe: String,
    val name: String,
    val title: String,
    val year: Int
)
