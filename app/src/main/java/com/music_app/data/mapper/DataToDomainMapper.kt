package com.music_app.data.mapper

import com.music_app.data.models.SearchTrackResponse
import com.music_app.data.models.TrackDTO
import com.music_app.data.models.TrackListResponse
import com.music_app.data.models.TrackResponse
import com.music_app.domain.models.Track
import javax.inject.Inject

class DataToDomainMapper @Inject constructor() {

    fun TrackListResponse.toDomain(): List<Track> {
        return tracks.data.map { it.toDomain() }
    }

    fun SearchTrackResponse.toDomain(): List<Track> {
        return data.map { it.toDomain() }
    }

    fun TrackResponse.toDomain(): Track {
        return Track(
            id = id,
            title = title,
            previewUrl = preview,
            duration = duration,
            artistName = artist.name,
            artistPicture = artist.pictureMedium,
            albumTitle = album.title,
            albumCover = album.coverMedium
        )
    }

    private fun TrackDTO.toDomain(): Track {
        return Track(
            id = id,
            title = title,
            previewUrl = preview,
            duration = duration,
            artistName = artist.name,
            artistPicture = artist.pictureMedium,
            albumTitle = album.title,
            albumCover = album.coverMedium
        )
    }
}