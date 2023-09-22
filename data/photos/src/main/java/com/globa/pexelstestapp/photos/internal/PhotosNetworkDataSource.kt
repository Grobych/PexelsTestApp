package com.globa.pexelstestapp.photos.internal

import com.globa.pexelstestapp.photos.api.Photo
import com.globa.pexelstestapp.photos.api.PhotoResource
import com.globa.pexeltestapp.network.api.PexelPhotosNetworkAPI
import javax.inject.Inject

class PhotosNetworkDataSource @Inject constructor(
    private val api: PexelPhotosNetworkAPI
) {
    suspend fun getCurated(): PhotoResource {
        val result = api.getCuratedPhotos()
        return if (result.isSuccessful) {
            PhotoResource.Success(
                photos = result.body()!!
                    .photos
                    .map {
                        Photo(
                            id = it.id,
                            url = it.sources.original
                        )
                    })
        } else {
            return PhotoResource.Error(result.toString())
        }
    }
    //TODO: rewrite this
    suspend fun getSearch(line: String): PhotoResource {
        val result = api.searchPhotos(line)
        return if (result.isSuccessful) {
            PhotoResource.Success(
                photos = result.body()!!
                    .photos
                    .map {
                        Photo(
                            id = it.id,
                            url = it.sources.original
                        )
                    })
        } else {
            return PhotoResource.Error(result.toString())
        }
    }

}