package com.globa.pexelstestapp.home

import androidx.paging.PagingData
import com.globa.pexelstestapp.photos.api.Photo
import kotlinx.coroutines.flow.Flow

sealed class HomeScreenPhotosUiState {
    data class Data(
        val data: Flow<PagingData<Photo>>?
    ): HomeScreenPhotosUiState()
}
