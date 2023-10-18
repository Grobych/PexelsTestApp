package com.globa.pexelstestapp.home.internal

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.SubcomposeAsyncImage
import com.globa.pexelstestapp.home.R
import com.globa.pexelstestapp.photos.api.Photo
import com.globa.pexelstestapp.ui.theme.PexelsTestAppTheme
import com.globa.pexelstestapp.ui.theme.photosGridColumnCount
import kotlinx.coroutines.flow.flowOf

@Composable
internal fun PhotoGrid(
    modifier: Modifier = Modifier,
    photos: LazyPagingItems<Photo>,
    onPhotoClick: (Int) -> Unit
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(count = photosGridColumnCount),
        modifier = modifier,
        verticalItemSpacing = 15.dp,
        contentPadding = PaddingValues(24.dp),
        horizontalArrangement = Arrangement.spacedBy(17.dp),
        content = {
            items(photos.itemCount) {
                photos[it]?.let { photo ->
                    SubcomposeAsyncImage(
                        modifier = Modifier
                            .clickable(
                                onClick = { onPhotoClick(photo.id) },
                            )
                            .clip(MaterialTheme.shapes.medium),
                        model = photo.url,
                        loading = {
                            Image(
                                painter = painterResource(id = R.drawable.ic_placeholder),
                                contentDescription = "Photo ${photo.id}"
                            )
                        },
                        contentDescription = "Photo ${photo.id}",
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    )
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PhotoGridPreview() {
    val photos = flowOf(
        PagingData.from(
            listOf(
                Photo(
                    id = 1,
                    url = "https://placebear.com/g/200/200"
                ),
                Photo(
                    id = 2,
                    url = "https://cdn.shopify.com/s/files/1/1830/5085/products/VE0007_BCAA_Capsule_90ct_2048x2048.png?v=1494855182"
                ),
                Photo(
                    id = 3,
                    url = "https://source.unsplash.com/user/c_v_r/1900×800"
                ),
                Photo(
                    id = 4,
                    url = "https://via.placeholder.com/300.png/09f/fff"
                ),
                Photo(
                    id = 5,
                    url = "https://placebear.com/g/200/200"
                ),
            )
        )
    ).collectAsLazyPagingItems()
    PexelsTestAppTheme {
        Surface(
            modifier = Modifier.size(width = 360.dp, height = 480.dp)
        ) {
            PhotoGrid(photos = photos, onPhotoClick = {})
        }
    }
}