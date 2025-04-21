package com.music_app.data.repository

import android.media.MediaPlayer
import com.music_app.domain.models.Track
import com.music_app.domain.repository.MusicRepository
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(
    private val mediaPlayer: MediaPlayer
) : MusicRepository {
}