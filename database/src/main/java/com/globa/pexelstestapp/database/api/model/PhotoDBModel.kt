package com.globa.pexelstestapp.database.api.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos")
data class PhotoDBModel(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val url: String,
    val photographer: String,
    val desc: String,
    @Embedded val imageURL: ImageURL
)


data class ImageURL(
    @ColumnInfo(name = "image_url_original") val original: String,
    @ColumnInfo(name = "image_url_extra_large") val extraLarge: String,
    @ColumnInfo(name = "image_url_large") val large: String,
    @ColumnInfo(name = "image_url_medium") val medium: String,
    @ColumnInfo(name = "image_url_small") val small: String,
    @ColumnInfo(name = "image_url_portrait") val portrait: String,
    @ColumnInfo(name = "image_url_landscape") val landscape: String,
    @ColumnInfo(name = "image_url_tiny") val tiny: String
)
