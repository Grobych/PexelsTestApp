package com.globa.pexeltestapp.network.internal


import com.globa.pexeltestapp.network.api.PexelCollectionsNetworkAPI
import com.globa.pexeltestapp.network.api.PexelPhotosNetworkAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit
        .Builder()
        .client(provideOkHttpClient())
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providePexelPhotosNetworkApi(retrofit: Retrofit): PexelPhotosNetworkAPI =
        retrofit.create(PexelPhotosNetworkAPI::class.java)

    @Provides
    @Singleton
    fun providePexelCollectionsNetworkApi(retrofit: Retrofit): PexelCollectionsNetworkAPI =
        retrofit.create(PexelCollectionsNetworkAPI::class.java)
}