package com.globa.pexelstestapp.featured.api

interface FeaturedCollectionsRepository {
    suspend fun getFeaturedCollections(): FeaturedCollectionResource
}