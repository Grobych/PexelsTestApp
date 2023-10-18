package com.globa.pexelstestapp.database.api.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.globa.pexelstestapp.database.api.model.PhotoDBModel
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotosDao {
    @Query("select * from photos")
    fun getPhotos(): PagingSource<Int, PhotoDBModel>
    @Query("select * from photos where id = :id")
    fun getPhotoById(id: Int): Flow<PhotoDBModel>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(photo : List<PhotoDBModel>)
    @Delete
    fun removePhoto(photo: PhotoDBModel)
    @Query("DELETE FROM photos")
    fun clearAll()
    @Query("select MAX(timestamp) from photos")
    fun getLastUpdated(): Long
}