package com.globa.pexelstestapp.photos.api

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    suspend fun getPhotos(searchLine: String = ""): Flow<PagingData<Photo>>

}