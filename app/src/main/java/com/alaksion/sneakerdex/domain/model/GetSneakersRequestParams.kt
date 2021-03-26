package com.alaksion.sneakerdex.domain.model

data class GetSneakersRequestParams(
    val limit : String,
    val page: String?,
    val styleId: String?,
    val name: String?,
    val shoe: String?,
    val brand : String?,
    val gender: String?,
    val colorway: String?,
    val releaseDate: String?,
    val releaseYear: String?,
    val sort: String?
)