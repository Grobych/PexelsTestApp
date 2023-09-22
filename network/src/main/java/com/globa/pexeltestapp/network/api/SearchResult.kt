package com.globa.pexeltestapp.network.api

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("total_results") val total: Int,
    @SerializedName("page") val page: Int,
    @SerializedName("per_page") val itemsPerPage: Int,
    @SerializedName("photos") val photos: List<PhotoResource>,
    @SerializedName("prev_page") val previousPageURL: String?,
    @SerializedName("next_page") val nextPageURL: String?
)
