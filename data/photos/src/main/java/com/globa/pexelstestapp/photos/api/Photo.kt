package com.globa.pexelstestapp.photos.api

data class Photo(
    val id: Int,
    val url: String
)

sealed class PhotoResource {
    data class Success(val photos: List<Photo>): PhotoResource()
    data class Error(val message: String): PhotoResource()
}
