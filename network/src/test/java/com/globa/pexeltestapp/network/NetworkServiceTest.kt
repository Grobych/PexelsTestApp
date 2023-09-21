package com.globa.pexeltestapp.network

import com.globa.pexeltestapp.network.internal.NetworkModule
import com.globa.pexeltestapp.network.internal.baseUrl
import kotlinx.coroutines.test.runTest
import org.junit.Test

class NetworkServiceTest() {

    @Test
    fun retrofitInitTest() {
        val retrofit = NetworkModule().provideRetrofit()
        assert(retrofit.baseUrl().toString() == baseUrl)
    }
    @Test
    fun getPhotoResponseTest() = runTest {
        val id = 2014422
        val networkApi = NetworkModule().providePexelApi(NetworkModule().provideRetrofit())
        val result = networkApi.getPhoto(id)
        assert(result.isSuccessful)
        assert(result.code() == 200)
        assert(result.body() != null)
        assert(result.body()!!.id == id)
    }

    @Test
    fun getCuratedTest() = runTest {
        val perPage = 30
        val networkApi = NetworkModule().providePexelApi(NetworkModule().provideRetrofit())
        val result = networkApi.getCuratedPhotos(itemsPerPage = perPage)
        assert(result.isSuccessful)
        assert(result.body() != null)
        assert(result.body()!!.itemsPerPage == perPage)
        assert(result.body()!!.photos.size == perPage)
    }

    @Test
    fun getSearchTest() = runTest {
        val testLine = "Nature"
        val networkApi = NetworkModule().providePexelApi(NetworkModule().provideRetrofit())
        val result = networkApi.searchPhotos(line = testLine)
        assert(result.isSuccessful)
        assert(result.body() != null)
        assert(result.body()!!.photos.isNotEmpty())
    }
}