package com.globa.pexelstestapp.photos.internal

import androidx.paging.PagingState
import androidx.room.withTransaction
import com.globa.pexelstestapp.database.api.PhotosDatabase
import com.globa.pexelstestapp.database.api.model.PhotoDBModel
import com.globa.pexelstestapp.database.api.model.PhotoRemoteKey
import javax.inject.Inject

class PhotoRemoteKeyDataSource @Inject constructor(
    private val database: PhotosDatabase
) {
    suspend fun getRemoteKeyForLastItem(state: PagingState<Int, PhotoDBModel>): PhotoRemoteKey? {
        return state.lastItemOrNull()?.let { photos ->
            database.withTransaction { database.photosRemoteKeyDao.getRemoteKeyByPhotoId(photos.id) }
        }
    }
    suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, PhotoDBModel>): PhotoRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                database.withTransaction { database.photosRemoteKeyDao.getRemoteKeyByPhotoId(id) }
            }
        }
    }
}