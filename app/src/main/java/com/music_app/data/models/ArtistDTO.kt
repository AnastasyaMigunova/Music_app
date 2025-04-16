package com.music_app.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtistDTO(
    val id: Long,
    val name: String,
    val link: String,
    val picture: String,
    @Json(name = "picture_small") val pictureSmall: String,
    @Json(name = "picture_medium") val pictureMedium: String,
    @Json(name = "picture_big") val pictureBig: String,
    @Json(name = "picture_xl") val pictureXl: String,
    val radio: Boolean,
    @Json(name = "tracklist") val trackList: String,
    val type: String
)