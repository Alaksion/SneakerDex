package com.alaksion.sneakerdex.data.remote

import com.alaksion.sneakerdex.data.model.SneakerListResponseData
import com.alaksion.sneakerdex.data.model.SneakerResponseData
import com.alaksion.sneakerdex.core.network.RetrofitClient
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SneakersService {

    @GET("/v1/sneakers")
    suspend fun getSneakers(
        @Query("limit")
        limit: String,

        @Query("page")
        page: String?,

        @Query("styleId")
        styleId: String?,

        @Query("name")
        name: String?,

        @Query("shoe")
        shoe: String?,

        @Query("brand")
        brand: String?,

        @Query("gender")
        gender: String?,

        @Query("colorway")
        colorway: String?,

        @Query("releaseDate")
        releaseDate: String?,

        @Query("releaseYear")
        releaseYear: String?,

        @Query("sort")
        sort: String?
    ): Response<SneakerListResponseData>

    @GET("/v1/sneakers/{sneakerId}")
    suspend fun getSneaker(@Path(value = "sneakerId") sneakerId: String): Response<SneakerResponseData>

    companion object {
        val INSTANCE = RetrofitClient.createService(SneakersService::class.java)
    }
}