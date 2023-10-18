package com.globa.pexelstestapp.photos.api

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    suspend fun getCurated(): Flow<PagingData<Photo>>
    suspend fun searchPhoto(searchLine: String = ""): Flow<PagingData<Photo>>
}