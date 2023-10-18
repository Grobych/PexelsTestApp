package com.globa.pexelstestapp.photos.internal

import com.globa.pexeltestapp.network.api.PexelPhotosNetworkAPI
import com.globa.pexeltestapp.network.api.model.PhotoResource
import javax.inject.Inject

class PhotosNetworkDataSource @Inject constructor(
    private val api: PexelPhotosNetworkAPI
) {

    suspend fun getCurated(page: Int): PhotosNetworkResponse {
        val result = api.getCuratedPhotos(pageNumber = page).body()
        return PhotosNetworkResponse(
            currentPage = result!!.page,
            nextPage = result.nextPageURL,
            prevPage = result.previousPageURL,
            photos = result.photos
        )
    }

    suspend fun search(line: String, page: Int): PhotosNetworkResponse {
        val result = api.searchPhotos(line = line, pageNumber = page).body()
        return PhotosNetworkResponse(
            currentPage = result!!.page,
            nextPage = result.nextPageURL,
            prevPage = result.previousPageURL,
            photos = result.photos
        )
    }
}

data class PhotosNetworkResponse(
    val currentPage: Int,
    val nextPage: String?,
    val prevPage: String?,
    val photos: List<PhotoResource>
)