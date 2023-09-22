package com.globa.pexelstestapp.photos.internal

import com.globa.pexelstestapp.photos.api.PhotoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface PhotoRepositoryBinding {
    @Binds
    fun bindPhotoRepository(bind: PhotoRepositoryImpl): PhotoRepository
}