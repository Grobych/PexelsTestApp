package com.globa.pexelstestapp.photos.internal

import com.globa.pexelstestapp.photos.api.Photo
import com.globa.pexeltestapp.network.api.PexelPhotosNetworkAPI
import com.globa.pexeltestapp.network.api.model.CuratedResult
import com.globa.pexeltestapp.network.api.model.SearchResult
import javax.inject.Inject

class PhotosNetworkDataSource @Inject constructor(
    private val api: PexelPhotosNetworkAPI
) {

    suspend fun getPhotos(line: String, page: Int): List<Photo> {
        val result = if (line.isEmpty()) api.getCuratedPhotos(pageNumber = page)
                                    else api.searchPhotos(line = line, pageNumber = page)
        val photos = when (val r = result.body()) {
            is CuratedResult -> r.photos
            is SearchResult -> r.photos
            else -> listOf()
        }
        return photos
                .map {
                    Photo(
                        id = it.id,
                        url = it.sources.original
                    )
                }
        }
}