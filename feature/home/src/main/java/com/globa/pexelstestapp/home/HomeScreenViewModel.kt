package com.globa.pexelstestapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.globa.pexelstestapp.featured.api.FeaturedCollectionsRepository
import com.globa.pexelstestapp.photos.api.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val photoRepository: PhotoRepository,
    private val featuredCollectionsRepository: FeaturedCollectionsRepository
): ViewModel() {
    private val _searchLine = MutableStateFlow("")
    val searchLine = _searchLine.asStateFlow()

    private val _photosUiState = MutableStateFlow<HomeScreenPhotosUiState>(HomeScreenPhotosUiState.Init)
    val photosUiState = _photosUiState.asStateFlow()

    fun updateSearchLine(line: String) = _searchLine.update { line }

    init {
        searchFieldInit()
    }
    @OptIn(FlowPreview::class)
    fun searchFieldInit() {
        searchLine
            .debounce(1000)
            .onEach {
                if (it.isEmpty()) fetchCurated()
                else searchPhotos(it)
            }
            .launchIn(viewModelScope)
    }
    private suspend fun fetchCurated() {
        _photosUiState.value =
            HomeScreenPhotosUiState.Data(
                data = photoRepository.getCurated()
            )
    }
    private suspend fun searchPhotos(line: String) {
        _photosUiState.value =
            HomeScreenPhotosUiState.Data(
                data = photoRepository.searchPhoto(line)
            )
    }

}