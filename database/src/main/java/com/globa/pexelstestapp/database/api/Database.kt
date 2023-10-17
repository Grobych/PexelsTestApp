package com.globa.pexelstestapp.database.api

import androidx.room.Database
import androidx.room.RoomDatabase
import com.globa.pexelstestapp.database.api.dao.PhotosDao
import com.globa.pexelstestapp.database.api.dao.PhotosRemoteKeyDao
import com.globa.pexelstestapp.database.api.model.PhotoDBModel
import com.globa.pexelstestapp.database.api.model.PhotoRemoteKey

@Database(
    entities = [PhotoDBModel::class, PhotoRemoteKey::class],
    version = 1,
    exportSchema = false
)

abstract class PhotosDatabase : RoomDatabase() {
    abstract val photosDao: PhotosDao
    abstract val photosRemoteKeyDao: PhotosRemoteKeyDao
}