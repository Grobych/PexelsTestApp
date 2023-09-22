package com.globa.pexelstestapp.featured.api

data class FeaturedCollection(
    val title: String
)

sealed class FeaturedCollectionResource {
    data class Success(val featuredCollection: List<FeaturedCollection>): FeaturedCollectionResource()
    data class Error(val error: String): FeaturedCollectionResource()
}
