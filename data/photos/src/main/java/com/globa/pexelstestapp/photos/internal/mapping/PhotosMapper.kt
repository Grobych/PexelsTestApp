package com.globa.pexelstestapp.photos.internal.mapping

import com.globa.pexelstestapp.database.api.model.ImageURL
import com.globa.pexelstestapp.database.api.model.PhotoDBModel
import com.globa.pexelstestapp.photos.api.Photo
import com.globa.pexeltestapp.network.api.model.ImageSources
import com.globa.pexeltestapp.network.api.model.PhotoResource
import java.util.Date

fun PhotoResource.asDBModel(): PhotoDBModel =
    PhotoDBModel(
        id = this.id,
        url= this.url,
        photographer = this.photographer,
        desc = this.description,
        imageURL = this.sources.asDBModel(),
        timestamp = Date().time
    )

fun ImageSources.asDBModel(): ImageURL =
    ImageURL(
        original, extraLarge, large, medium, small, portrait, landscape, tiny
    )

fun PhotoDBModel.asDomainModel(): Photo =
    Photo(
        id = this.id,
        url = this.imageURL.medium
    )