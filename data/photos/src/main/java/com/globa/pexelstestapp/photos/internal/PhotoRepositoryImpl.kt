package com.globa.pexelstestapp.photos.internal

import com.globa.pexelstestapp.photos.api.PhotoRepository
import com.globa.pexelstestapp.photos.api.PhotoResource
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val photosNetworkDataSource: PhotosNetworkDataSource
): PhotoRepository {
    override suspend fun getPhotos(searchLine: String): PhotoResource {
        return if (searchLine.isEmpty()) photosNetworkDataSource.getCurated()
        else photosNetworkDataSource.getSearch(line = searchLine)
    }
}