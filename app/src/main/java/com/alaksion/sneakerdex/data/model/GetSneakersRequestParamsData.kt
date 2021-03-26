package com.alaksion.sneakerdex.data.model

import com.alaksion.sneakerdex.domain.model.GetSneakersRequestParams

data class GetSneakersRequestParamsData(
    val limit: String,
    val page: String?,
    val styleId: String?,
    val name: String?,
    val shoe: String?,
    val brand: String?,
    val gender: String?,
    val colorway: String?,
    val releaseDate: String?,
    val releaseYear: String?,
    val sort: String?
)

fun GetSneakersRequestParams.mapToData(): GetSneakersRequestParamsData =
    GetSneakersRequestParamsData(
        limit = this.limit,
        page = this.page,
        styleId = this.styleId,
        name = this.name,
        shoe = this.shoe,
        brand = this.brand,
        gender = this.gender,
        colorway = this.colorway,
        releaseDate = this.releaseDate,
        releaseYear = this.releaseYear,
        sort = this.sort
    )
