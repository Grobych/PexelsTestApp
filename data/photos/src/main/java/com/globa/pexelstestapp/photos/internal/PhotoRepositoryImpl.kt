package com.globa.pexelstestapp.photos.internal

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.globa.pexelstestapp.photos.api.Photo
import com.globa.pexelstestapp.photos.api.PhotoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val photosNetworkDataSource: PhotosNetworkDataSource
): PhotoRepository {
    override suspend fun getPhotos(searchLine: String): Flow<PagingData<Photo>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {PhotosPagingSource(
            dataSource = photosNetworkDataSource,
            searchLine = searchLine
        )}
    ).flow
}