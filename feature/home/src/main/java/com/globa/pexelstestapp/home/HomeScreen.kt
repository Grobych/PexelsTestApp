package com.globa.pexelstestapp.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.globa.pexelstestapp.home.internal.NetworkErrorPlaceholder
import com.globa.pexelstestapp.home.internal.NoItemsPlaceholder
import com.globa.pexelstestapp.home.internal.PhotoGrid
import com.globa.pexelstestapp.home.internal.ScrollAnimation
import com.globa.pexelstestapp.home.internal.SearchField

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    onPhotoClick: (Int) -> Unit
) {
    val uiState = viewModel.photosUiState.collectAsState()
    val searchLine = viewModel.searchLine.collectAsState()
    val showLoadingAnimation = viewModel.showLoadingAnimation.collectAsState()

    val onSearchLineChanged = fun(line: String) {
        viewModel.updateSearchLine(line)
    }
    val onExploreButtonClick = fun() {
        viewModel.updateSearchLine("")
    }
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Header(searchLine = searchLine.value, onSearchLineChanged = onSearchLineChanged)
                if (showLoadingAnimation.value)
                    ScrollAnimation(modifier = Modifier.padding(start = 4.dp, end = 4.dp))
            }
        }
    ) {
        when (val state = uiState.value) {
            is HomeScreenPhotosUiState.Data -> {
                state.data?.let { data ->
                    val photos = data.collectAsLazyPagingItems()
                    val errorState = when {
                        photos.loadState.prepend is LoadState.Error -> photos.loadState.prepend as LoadState.Error
                        photos.loadState.append is LoadState.Error -> photos.loadState.append as LoadState.Error
                        photos.loadState.refresh is LoadState.Error -> photos.loadState.refresh as LoadState.Error
                        else -> null
                    }
                    if (errorState != null) {
                        NetworkErrorPlaceholder(
                            modifier = Modifier.padding(it),
                            onRefreshButtonClick = { photos.refresh() }
                        )
                    } else {
                        if (photos.itemCount == 0 && searchLine.value.isNotEmpty()) {
                            NoItemsPlaceholder(
                                modifier = Modifier.padding(it),
                                onExploreButtonClick = onExploreButtonClick
                            )
                        } else {
                            PhotoGrid(
                                modifier = Modifier.padding(it),
                                photos = data.collectAsLazyPagingItems(),
                                onPhotoClick = onPhotoClick
                            )
                        }
                    }
                }
            }
        }
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