package com.globa.pexelstestapp.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.globa.pexelstestapp.home.internal.NoItemsPlaceholder
import com.globa.pexelstestapp.home.internal.PhotoGrid
import com.globa.pexelstestapp.home.internal.SearchField

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    onPhotoClick: (Int) -> Unit
) {
    val uiState = viewModel.photosUiState.collectAsState()
    val searchLine = viewModel.searchLine.collectAsState()
    val onSearchLineChanged = fun(line: String) {
        viewModel.updateSearchLine(line)
    }
    val onExploreButtonClick = fun() {
        viewModel.updateSearchLine("")
    }
    Header(searchLine = searchLine.value, onSearchLineChanged = onSearchLineChanged)

    when (val state = uiState.value) {
        is HomeScreenPhotosUiState.Data -> {
            val photos = state.data.collectAsLazyPagingItems()
            if (photos.itemCount > 0) {
                PhotoGrid(photos = state.data.collectAsLazyPagingItems(), onPhotoClick = onPhotoClick)
            } else {
                NoItemsPlaceholder(
                    onExploreButtonClick = onExploreButtonClick
                )
            }
        }
        HomeScreenPhotosUiState.Init -> {}
        HomeScreenPhotosUiState.NetworkConnectionError -> TODO()
    }
}

@Composable
private fun Header(
    searchLine: String,
    onSearchLineChanged: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        SearchField(
            modifier = Modifier
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 12.dp,
                    bottom = 12.dp
                )
                .fillMaxWidth(),
            text = searchLine,
            onTextFieldChanged = onSearchLineChanged
        )
    }
}