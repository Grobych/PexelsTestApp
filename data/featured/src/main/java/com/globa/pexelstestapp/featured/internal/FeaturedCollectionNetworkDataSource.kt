package com.globa.pexelstestapp.featured.internal


import com.globa.pexelstestapp.featured.api.FeaturedCollection
import com.globa.pexelstestapp.featured.api.FeaturedCollectionResource
import com.globa.pexeltestapp.network.api.PexelCollectionsNetworkAPI
import javax.inject.Inject

class FeaturedCollectionNetworkDataSource @Inject constructor(
    private val api: PexelCollectionsNetworkAPI
) {
    suspend fun getFeatured(): FeaturedCollectionResource {
        val result = api.getFeaturedCollections()
        return if (result.isSuccessful) {
            FeaturedCollectionResource.Success(
                result.body()!!
                    .collection
                    .map { FeaturedCollection(it.title) }
            )
        } else {
            FeaturedCollectionResource.Error(result.message())
        }
    }
}