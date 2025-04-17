package com.music_app.ui.api_tracks_screen

import androidx.lifecycle.ViewModel
import com.music_app.domain.usecase.api_tracks.GetTracksUseCase
import com.music_app.ui.models.TrackVO
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

data class ApiTracksState(
    val isLoading: Boolean = false,
    val tracks: List<TrackVO> = emptyList(),
    val errorMessage: String? = null
)

sealed class ApiTracksSideEffect {
    data class ShowError(val message: String) : ApiTracksSideEffect()
    data class NavigateTo(val id: Long) : ApiTracksSideEffect()
}

@HiltViewModel
class ApiTracksViewModel @Inject constructor(
    private val getTracksUseCase: GetTracksUseCase
) : ContainerHost<ApiTracksState, ApiTracksSideEffect>, ViewModel() {

    override val container = container<ApiTracksState, ApiTracksSideEffect>(ApiTracksState())

    fun loadTracks() = intent {
        reduce { state.copy(isLoading = true) }

        val result = getTracksUseCase.getTracks()

        result
            .onSuccess { tracks ->
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
                postSideEffect(ApiTracksSideEffect.ShowError(errorMessage))
            }
    }

    fun navigate(id: Long) = intent {
        postSideEffect(ApiTracksSideEffect.NavigateTo(id))
    }
}