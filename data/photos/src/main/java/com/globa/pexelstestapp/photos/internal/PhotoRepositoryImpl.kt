package com.globa.pexelstestapp.photos.internal

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.globa.pexelstestapp.database.api.PhotosDatabase
import com.globa.pexelstestapp.photos.api.Photo
import com.globa.pexelstestapp.photos.api.PhotoRepository
import com.globa.pexelstestapp.photos.internal.mapping.asDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val photosNetworkDataSource: PhotosNetworkDataSource,
    private val remoteKeyDataSource: PhotoRemoteKeyDataSource,
    private val database: PhotosDatabase
): PhotoRepository {
    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getCurated(): Flow<PagingData<Photo>> = Pager(
        config = PagingConfig(pageSize = pageSize),
        remoteMediator = PhotosRemoteMediator(
            database = database,
            networkDataSource = photosNetworkDataSource,
            remoteKeyDataSource = remoteKeyDataSource
        ),
        pagingSourceFactory = {
            database.photosDao.getPhotos()
        }
    ).flow.map { pagingData -> pagingData.map { it.asDomainModel() } }

    override suspend fun searchPhoto(searchLine: String):
        Flow<PagingData<Photo>> = Pager(
            config = PagingConfig(pageSize = pageSize),
            pagingSourceFactory = {SearchPhotoPagingSource(searchLine,photosNetworkDataSource)}
        ).flow
}