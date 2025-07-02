package com.music_app.domain.usecase.play_track

import com.music_app.domain.models.Track
import com.music_app.domain.service.MusicController
import javax.inject.Inject

class AddTracksUseCase @Inject constructor(
    private val musicController: MusicController
) {
    operator fun invoke(tracks: List<Track>) {
        musicController.addTracks(tracks)
    }
}