package com.globa.pexeltestapp.network.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PexelCollectionsNetworkAPI {
    @GET("collections/featured")
    suspend fun getFeaturedCollections(
        @Query(value = "per_page") perPage :Int = 7
    ): Response<FeaturedCollection>
}