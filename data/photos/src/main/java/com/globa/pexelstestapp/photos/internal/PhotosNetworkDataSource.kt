package com.globa.pexelstestapp.photos.internal

import com.globa.pexeltestapp.network.api.PexelPhotosNetworkAPI
import com.globa.pexeltestapp.network.api.model.CuratedResult
import com.globa.pexeltestapp.network.api.model.PhotoResource
import com.globa.pexeltestapp.network.api.model.SearchResult
import javax.inject.Inject

class PhotosNetworkDataSource @Inject constructor(
    private val api: PexelPhotosNetworkAPI
) {

    suspend fun getPhotos(line: String, page: Int): PhotosNetworkResponse {
        val result = if (line.isEmpty()) api.getCuratedPhotos(pageNumber = page)
        else api.searchPhotos(line = line, pageNumber = page)
        return when (val r = result.body()) {
            is CuratedResult -> PhotosNetworkResponse(
                currentPage = r.page,
                nextPage = r.nextPageURL,
                prevPage = r.previousPageURL,
                photos = r.photos
            )

            is SearchResult -> PhotosNetworkResponse(
                currentPage = r.page,
                nextPage = r.nextPageURL,
                prevPage = r.previousPageURL,
                photos = r.photos
            )

            else -> {
                PhotosNetworkResponse(
                    currentPage = 0,
                    nextPage = null,
                    prevPage = null,
                    photos = emptyList() //TODO: use sealed?
                )
            }
        }
    }
}

data class PhotosNetworkResponse(
    val currentPage: Int,
    val nextPage: String?,
    val prevPage: String?,
    val photos: List<PhotoResource>
)