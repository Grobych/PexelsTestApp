package com.globa.pexelstestapp.photos.api

interface PhotoRepository {
    suspend fun getPhotos(searchLine: String = ""): PhotoResource

}