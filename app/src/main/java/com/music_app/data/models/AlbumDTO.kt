package com.music_app.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AlbumDTO(
    val id: Long,
    val title: String,
    val cover: String,
    @Json(name = "cover_small") val coverSmall: String,
    @Json(name = "cover_medium") val coverMedium: String,
    @Json(name = "cover_big") val coverBig: String,
    @Json(name = "cover_xl") val coverXl: String,
    @Json(name = "md5_image") val md5Image: String,
    @Json(name = "tracklist") val trackList: String,
    val type: String
)
