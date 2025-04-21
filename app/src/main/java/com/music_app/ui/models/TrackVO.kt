package com.music_app.ui.models

data class TrackVO(
    val id: Long,
    val title: String,
    val previewUrl: String,
    val duration: Int,
    val artistName: String,
    val artistPicture: String,
    val albumTitle: String,
    val albumCover: String
)
