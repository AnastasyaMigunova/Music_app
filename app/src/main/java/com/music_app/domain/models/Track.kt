package com.music_app.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Track(
    val id: Long,
    val title: String,
    val previewUrl: String,
    val duration: Int,
    val artistName: String,
    val artistPicture: String,
    val albumTitle: String,
    val albumCover: String
): Parcelable
