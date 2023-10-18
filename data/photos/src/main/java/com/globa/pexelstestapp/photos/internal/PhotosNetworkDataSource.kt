package com.globa.pexelstestapp.photos.internal

import com.globa.pexeltestapp.network.api.PexelPhotosNetworkAPI
import com.globa.pexeltestapp.network.api.model.PhotoResource
import javax.inject.Inject

class PhotosNetworkDataSource @Inject constructor(
    private val api: PexelPhotosNetworkAPI
) {

    suspend fun getCurated(page: Int): PhotosNetworkResponse {
        val result = api.getCuratedPhotos(pageNumber = page)
        val body = result.body()
        return if (result.isSuccessful && body != null) {
            PhotosNetworkResponse.Ok(
                currentPage = body.page,
                nextPage = body.nextPageURL,
                prevPage = body.previousPageURL,
                photos = body.photos
            )
        } else {
            PhotosNetworkResponse.Error(
                code = result.code(),
                message = result.message()
            )
        }
    }

    suspend fun search(line: String, page: Int): PhotosNetworkResponse {
        val result = api.searchPhotos(line = line, pageNumber = page)
        val body = result.body()
        return if (result.isSuccessful && body != null) {
            PhotosNetworkResponse.Ok(
                currentPage = body.page,
                nextPage = body.nextPageURL,
                prevPage = body.previousPageURL,
                photos = body.photos
            )
        } else {
            PhotosNetworkResponse.Error(
                code = result.code(),
                message = result.message()
            )
        }
    }
}

sealed class PhotosNetworkResponse{
    data class Ok(
        val currentPage: Int,
        val nextPage: String?,
        val prevPage: String?,
        val photos: List<PhotoResource>
    ): PhotosNetworkResponse()
    data class Error(
        val code: Int = 400,
        val message: String = "",
        val e: Throwable? = null
    ): PhotosNetworkResponse()
}