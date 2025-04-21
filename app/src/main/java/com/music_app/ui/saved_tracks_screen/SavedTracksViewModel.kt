package com.music_app.ui.saved_tracks_screen

import androidx.lifecycle.ViewModel
import com.music_app.domain.usecase.saved_tracks.GetSavedTracksUseCase
import com.music_app.ui.models.TrackVO
import com.music_app.ui.play_track_screen.PlayTrackSource
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

data class SavedTracksState(
    val isLoading: Boolean = false,
    val tracks: List<TrackVO> = emptyList(),
    val errorMessage: String? = null
)

sealed class SavedTracksSideEffect {
    data class ShowError(val message: String) : SavedTracksSideEffect()
    data class NavigateTo(val id: Long, val playTrackSource: PlayTrackSource) : SavedTracksSideEffect()
}

@HiltViewModel
class SavedTracksViewModel @Inject constructor(
    private val getSavedTracksUseCase: GetSavedTracksUseCase
) : ContainerHost<SavedTracksState, SavedTracksSideEffect>, ViewModel() {

    override val container = container<SavedTracksState, SavedTracksSideEffect>(
        SavedTracksState()
    )
    private var savedTracks: List<TrackVO> = emptyList()

    fun loadTracks() = intent {
        reduce { state.copy(isLoading = true) }

        val result = getSavedTracksUseCase.getTracks()

        result
            .onSuccess { tracks ->
                savedTracks = tracks
                reduce {
                    state.copy(
                        isLoading = false,
                        tracks = tracks,
                        errorMessage = null
                    )
                }
            }
            .onFailure { exception ->
                val errorMessage = exception.message ?: "Неизвестная ошибка"
                reduce { state.copy(isLoading = false, errorMessage = errorMessage) }
                postSideEffect(SavedTracksSideEffect.ShowError(errorMessage))
            }
    }

    fun searchTracks(query: String) = intent {
        val filtered = savedTracks.filter {
            it.title.contains(query, ignoreCase = true) ||
                    it.artistName.contains(query, ignoreCase = true) ||
                    it.albumTitle.contains(query, ignoreCase = true)
        }
        reduce { state.copy(tracks = filtered) }
    }

    fun navigate(id: Long, playTrackSource: PlayTrackSource) = intent {
        postSideEffect(SavedTracksSideEffect.NavigateTo(id, playTrackSource))
    }
}