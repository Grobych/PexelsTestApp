package com.globa.pexelstestapp.database.api

import androidx.room.Database
import androidx.room.RoomDatabase
import com.globa.pexelstestapp.database.api.dao.PhotosDao
import com.globa.pexelstestapp.database.api.model.PhotoDBModel

@Database(
    entities = [PhotoDBModel::class],
    version = 1,
    exportSchema = false
)

abstract class PhotosDatabase : RoomDatabase() {
    abstract val photosDao: PhotosDao
}