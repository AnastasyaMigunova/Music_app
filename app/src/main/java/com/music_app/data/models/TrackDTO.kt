package com.music_app.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TrackDTO(
    val id: Long,
    val title: String,
    @Json(name = "title_short") val titleShort: String,
    @Json(name = "title_version") val titleVersion: String,
    val link: String,
    val duration: Int,
    val rank: Int,
    @Json(name = "explicit_lyrics") val explicitLyrics: Boolean,
    @Json(name = "explicit_content_lyrics") val explicitContentLyrics: Int,
    @Json(name = "explicit_content_cover") val explicitContentCover: Int,
    val preview: String,
    @Json(name = "md5_image") val md5Image: String,
    val position: Int,
    val artist: ArtistDTO,
    val album: AlbumDTO,
    val type: String
)
