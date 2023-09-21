package com.globa.pexeltestapp.network.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PexelNetworkApi {
    @GET("photos/{id}")
    suspend fun getPhoto(@Path(value = "id") id: Int): Response<PhotoResource>

    @GET("curated")
    suspend fun getCuratedPhotos(
        @Query(value = "page") pageNumber: Int = 1,
        @Query(value = "per_page") itemsPerPage: Int = 30
    ): Response<CuratedResult>

    @GET("search")
    suspend fun searchPhotos(
        @Query(value = "query") line: String,
        @Query(value = "page") pageNumber: Int = 1,
        @Query(value = "per_page") itemsPerPage: Int = 30
    ): Response<SearchResult>
}