package com.music_app.domain.usecase.play_track

import com.music_app.domain.service.MusicController
import javax.inject.Inject

class PauseTrackUseCase @Inject constructor(
    private val musicController: MusicController
) {
    operator fun invoke() = musicController.pause()
}