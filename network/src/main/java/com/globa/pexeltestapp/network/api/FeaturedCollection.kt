package com.globa.pexeltestapp.network.api

import com.google.gson.annotations.SerializedName


data class FeaturedCollection(
    @SerializedName("collections") val collection: List<CollectionResource>,
    @SerializedName("page") val page: Int,
    @SerializedName("per_page") val itemsPerPage: Int,
    @SerializedName("total_results") val total: Int,
    @SerializedName("next_page") val nextPageURL: String?,
    @SerializedName("prev_page") val previousPageURL: String?
)

data class CollectionResource(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("private") val isPrivate: Boolean,
    @SerializedName("media_count") val mediaCount: Int,
    @SerializedName("photos_count") val photosCount: Int,
    @SerializedName("videos_count") val videosCount: Int
)
