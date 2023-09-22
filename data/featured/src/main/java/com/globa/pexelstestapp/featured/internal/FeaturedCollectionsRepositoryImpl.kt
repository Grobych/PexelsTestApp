package com.globa.pexelstestapp.featured.internal

import com.globa.pexelstestapp.featured.api.FeaturedCollectionResource
import com.globa.pexelstestapp.featured.api.FeaturedCollectionsRepository
import javax.inject.Inject

class FeaturedCollectionsRepositoryImpl @Inject constructor(
    private val featuredCollectionNetworkDataSource: FeaturedCollectionNetworkDataSource
): FeaturedCollectionsRepository {
    override suspend fun getFeaturedCollections(): FeaturedCollectionResource =
        featuredCollectionNetworkDataSource.getFeatured()
}