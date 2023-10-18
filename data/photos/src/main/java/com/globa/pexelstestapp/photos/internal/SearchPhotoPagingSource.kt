package com.globa.pexelstestapp.photos.internal

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.globa.pexelstestapp.photos.api.Photo

class SearchPhotoPagingSource(
    private val searchLine: String,
    private val networkDataSource: PhotosNetworkDataSource
): PagingSource<Int, Photo>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Photo> {
        return try {
            val page = params.key ?: 1
            when (val response = networkDataSource.search(searchLine, page)) {
                is PhotosNetworkResponse.Error -> {
                    return LoadResult.Error(Throwable("${response.code}: ${response.message}"))
                }
                is PhotosNetworkResponse.Ok -> {
                    val nextKey =
                        if (response.photos.isEmpty()) {
                            null
                        } else {
                            page + (params.loadSize / pageSize)
                        }
                    return LoadResult.Page(
                        data = response.photos.map { Photo(id = it.id, url = it.sources.medium) },
                        prevKey =  if (page == 1) null else page,
                        nextKey = nextKey
                    )
                }
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}