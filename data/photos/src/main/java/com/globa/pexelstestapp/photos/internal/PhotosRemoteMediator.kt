package com.globa.pexelstestapp.photos.internal

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.globa.pexelstestapp.database.api.PhotosDatabase
import com.globa.pexelstestapp.database.api.model.PhotoDBModel
import com.globa.pexelstestapp.database.api.model.PhotoRemoteKey
import com.globa.pexelstestapp.photos.internal.mapping.asDBModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.io.InvalidObjectException
import java.util.Date
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalPagingApi::class)
class PhotosRemoteMediator(
    private val database: PhotosDatabase,
    private val networkDataSource: PhotosNetworkDataSource,
    private val remoteKeyDataSource: PhotoRemoteKeyDataSource
): RemoteMediator<Int, PhotoDBModel>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PhotoDBModel>
    ): MediatorResult {
        return try {
            val page = when(loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = remoteKeyDataSource.getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: initialPage
                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(true)
                }
                LoadType.APPEND -> {
                    val remoteKeys = remoteKeyDataSource.getRemoteKeyForLastItem(state)
                        ?: throw InvalidObjectException("Result is empty")
                    remoteKeys.nextKey ?: return MediatorResult.Success(true)
                }
            }
            val response = networkDataSource.getCurated(
                page = page
            )
            val endOfPaginationReached = response.photos.size < state.config.pageSize

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.photosDao.clearAll()
                    database.photosRemoteKeyDao.clearAll()
                }
                val prevKey = if (page == initialPage) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = response.photos.map {
                    PhotoRemoteKey(photoId = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                database.photosDao.insertAll(response.photos.map { it.asDBModel() })
                database.photosRemoteKeyDao.insertAll(keys)
            }
            MediatorResult.Success(
                endOfPaginationReached = endOfPaginationReached
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    override suspend fun initialize(): InitializeAction {
        val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)
        return withContext(Dispatchers.IO) {
            if (Date().time - database.photosDao.getLastUpdated() <= cacheTimeout)
            {
                InitializeAction.SKIP_INITIAL_REFRESH
            } else {
                InitializeAction.LAUNCH_INITIAL_REFRESH
            }
        }
        }
}