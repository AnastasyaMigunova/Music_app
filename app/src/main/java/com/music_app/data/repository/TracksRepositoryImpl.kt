package com.music_app.data.repository

import android.util.Log
import com.music_app.data.api.ApiService
import com.music_app.data.mapper.DataToDomainMapper
import com.music_app.domain.models.Track
import com.music_app.domain.repository.TracksRepository
import javax.inject.Inject

class TracksRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dataToDomainMapper: DataToDomainMapper
) : TracksRepository {
    override suspend fun getTracks(): Result<List<Track>> {
        return try {
            val response = apiService.getTracks()
            val tracks = dataToDomainMapper.run { response.toDomain() }
            Result.success(tracks)
        } catch (e: Exception) {
            Log.e("TracksRepository", "Get tracks error", e)
            Result.failure(e)
        }
    }

    override suspend fun getTrackById(id: Long): Result<Track> {
        return try {
            val response = apiService.getTrackById(id)
            val trackById = dataToDomainMapper.run { response.toDomain() }
            Result.success(trackById)
        } catch (e: Exception) {
            Log.e("TracksRepository", "Get track by id error", e)
            Result.failure(e)
        }
    }

    override suspend fun getTracksByQuery(query: String): Result<List<Track>> {
        return try {
            val response = apiService.searchTracks(query)
            val searchTracks = dataToDomainMapper.run { response.toDomain() }
            Result.success(searchTracks)
        } catch (e: Exception) {
            Log.e("TracksRepository", "Get tracks by query error", e)
            Result.failure(e)
        }
    }
}