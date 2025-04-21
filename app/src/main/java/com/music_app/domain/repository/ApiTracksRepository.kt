package com.music_app.domain.repository

import com.music_app.domain.models.Track

interface ApiTracksRepository {
    suspend fun getTracks() : Result<List<Track>>
    suspend fun getTrackById(id: Long): Result<Track>
    suspend fun getTracksByQuery(query: String): Result<List<Track>>
}