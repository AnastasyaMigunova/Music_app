package com.music_app.data.mapper

import androidx.media3.common.MediaItem
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

    fun TrackDTO.toDomain(): Track {
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

    fun MediaItem.toDomain(): Track {
        return Track(
            id = mediaId.toLongOrNull() ?: 0L,
            title = mediaMetadata.title?.toString().orEmpty(),
            previewUrl = localConfiguration?.uri?.toString() ?: mediaId,
            duration = (mediaMetadata.extras?.getLong("DURATION") ?: 0L).toInt(),
            artistName = mediaMetadata.artist?.toString().orEmpty(),
            artistPicture = mediaMetadata.artworkUri?.toString().orEmpty(),
            albumTitle = mediaMetadata.albumTitle?.toString().orEmpty(),
            albumCover = mediaMetadata.artworkUri?.toString().orEmpty()
        )
    }
}