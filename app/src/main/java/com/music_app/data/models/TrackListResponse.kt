package com.music_app.data.models

data class TrackListResponse(
    val tracks: TracksData
)

data class TracksData(
    val data: List<TrackDTO>
)