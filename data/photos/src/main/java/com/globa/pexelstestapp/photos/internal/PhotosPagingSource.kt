package com.globa.pexelstestapp.photos.internal

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.globa.pexelstestapp.photos.api.Photo
import javax.inject.Inject

class PhotosPagingSource @Inject constructor(
    private val dataSource: PhotosNetworkDataSource,
    private val searchLine: String = ""
): PagingSource<Int, Photo>() {
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        return try {
            val page = params.key ?: 1
            val response = dataSource.getPhotos(line = searchLine, page = page)

            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}