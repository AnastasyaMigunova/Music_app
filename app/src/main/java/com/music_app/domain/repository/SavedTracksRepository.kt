package com.music_app.domain.repository

import com.music_app.domain.models.Track

interface SavedTracksRepository {
    suspend fun getTracks() : Result<List<Track>>
    suspend fun getTrackById(id: Long) : Result<Track>
}