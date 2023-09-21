package com.globa.pexeltestapp.network.api

import com.google.gson.annotations.SerializedName

data class CuratedResult(
    @SerializedName("page") val page: Int,
    @SerializedName("per_page") val itemsPerPage: Int,
    @SerializedName("photos") val photos: List<PhotoResource>,
    @SerializedName("next_page") val nextPageURL: String
)
