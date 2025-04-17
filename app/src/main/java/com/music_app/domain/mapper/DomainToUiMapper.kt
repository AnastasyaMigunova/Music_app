package com.music_app.domain.mapper

import com.music_app.domain.models.Track
import com.music_app.ui.models.TrackVO
import javax.inject.Inject

class DomainToUiMapper @Inject constructor() {

    fun Track.toViewObject(): TrackVO {
        return TrackVO(
            id = id,
            title = title,
            previewUrl = previewUrl,
            duration = duration,
            artistName = artistName,
            artistPicture = artistPicture,
            albumTitle = albumTitle,
            albumCover = albumCover
        )
    }
}
