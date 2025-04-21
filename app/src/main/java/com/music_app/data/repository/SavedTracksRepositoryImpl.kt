package com.music_app.data.repository

import android.content.Context
import android.util.Log
import com.music_app.data.tracksHelper.TracksHelper
import com.music_app.domain.models.Track
import com.music_app.domain.repository.SavedTracksRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SavedTracksRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val tracksHelper: TracksHelper
) : SavedTracksRepository {

    override suspend fun getTracks(): Result<List<Track>> {
        return try {
            val tracks = tracksHelper.getAudioFiles(context)
            Result.success(tracks)
        } catch (e: Exception) {
            Log.e("SavedTracksRepository", "Get saved tracks error", e)
            Result.failure(e)
        }
    }

    override suspend fun getTrackById(id: Long): Result<Track> {
        val track = tracksHelper.getAudioFileById(context, id)
        return if (track != null) {
            Result.success(track)
        } else {
            Result.failure(Exception("Track not found"))
        }
    }
}