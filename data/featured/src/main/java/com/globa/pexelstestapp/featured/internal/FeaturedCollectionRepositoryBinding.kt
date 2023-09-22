package com.globa.pexelstestapp.featured.internal

import com.globa.pexelstestapp.featured.api.FeaturedCollectionsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FeaturedCollectionRepositoryBinding {
    @Binds
    fun bindFeaturedCollectionRepository(bind: FeaturedCollectionsRepositoryImpl): FeaturedCollectionsRepository
}