package com.globa.pexelstestapp.photos.internal

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.globa.pexelstestapp.database.api.PhotosDatabase
import com.globa.pexelstestapp.database.api.model.PhotoDBModel
import com.globa.pexelstestapp.photos.internal.mapping.asDBModel
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PhotosRemoteMediator(
    private val line: String,
    private val database: PhotosDatabase,
    private val networkDataSource: PhotosNetworkDataSource
): RemoteMediator<Int, PhotoDBModel>() {

    @Volatile private var nextPage: Int? = null
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PhotoDBModel>
    ): MediatorResult {
        return try {
            val page = when(loadType) {
                LoadType.REFRESH -> {
                    0
                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(true)
                }
                LoadType.APPEND -> {
                    if(nextPage == null) {
                        return MediatorResult.Success(true)
                    }
                    nextPage
                }
            }
            val response = networkDataSource.getPhotos(
                line = line,
                page = page?:0
            )
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.photosDao.clearAll()
                }
                nextPage = if(response.nextPage == null) {
                    null
                } else {
                    response.currentPage.plus(1)
                }
                database.photosDao.insertAll(response.photos.map { it.asDBModel() })
            }
            MediatorResult.Success(
                endOfPaginationReached = response.photos.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}