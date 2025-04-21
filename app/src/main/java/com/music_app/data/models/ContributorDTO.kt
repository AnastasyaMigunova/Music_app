package com.music_app.data.models

import com.squareup.moshi.Json

data class ContributorDTO(
    val id: Long,
    val name: String,
    val link: String,
    val share: String,
    val picture: String,
    val pictureSmall: String,
    val pictureMedium: String,
    val pictureBig: String,
    val pictureXl: String,
    val radio: Boolean,
    @Json(name = "tracklist") val trackList: String,
    val type: String,
    val role: String
)
