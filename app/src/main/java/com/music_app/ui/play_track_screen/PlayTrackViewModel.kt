package com.music_app.ui.play_track_screen


import androidx.lifecycle.ViewModel
import com.music_app.domain.usecase.api_tracks.GetApiTrackByIdUseCase
import com.music_app.domain.usecase.saved_tracks.GetSavedTrackByIdUseCase
import com.music_app.ui.models.TrackVO
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

data class PlayTrackState(
    val isLoading: Boolean = false,
    val track: TrackVO? = null,
    val errorMessage: String? = null,
    val isPlaying: Boolean = false,
    val currentPosition: Long = 0L,
    val duration: Long = 0L
)

sealed class PlayTrackSideEffect {
    data class ShowError(val message: String) : PlayTrackSideEffect()
}

@HiltViewModel
class PlayTrackViewModel @Inject constructor(
    private val getApiTrackByIdUseCase: GetApiTrackByIdUseCase,
    private val getSavedTrackByIdUseCase: GetSavedTrackByIdUseCase
) : ContainerHost<PlayTrackState, PlayTrackSideEffect>, ViewModel() {

    override val container = container<PlayTrackState, PlayTrackSideEffect>(
        PlayTrackState()
    )

    fun loadTrack(id: Long, source: PlayTrackSource) = intent {
        reduce { state.copy(isLoading = true) }

        val result = when (source) {
            PlayTrackSource.FROM_API -> getApiTrackByIdUseCase.getTrackById(id)
            PlayTrackSource.FROM_SAVED -> getSavedTrackByIdUseCase.getTrackById(id)
        }

        result
            .onSuccess { track ->
                reduce { state.copy(isLoading = false, track = track) }
            }
            .onFailure { exception ->
                val errorMessage = exception.message ?: "Неизвестная ошибка"
                reduce { state.copy(isLoading = false, errorMessage = errorMessage) }
                postSideEffect(PlayTrackSideEffect.ShowError(errorMessage))
            }
    }

    fun togglePlayPause() = intent {
        state.track?.let {
            val isPlaying = !state.isPlaying
            reduce { state.copy(isPlaying = isPlaying) }
        }
    }

    fun updateProgress(position: Long) = intent {
        reduce { state.copy(currentPosition = position) }
    }
}