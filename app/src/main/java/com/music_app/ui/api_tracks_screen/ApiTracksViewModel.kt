package com.music_app.ui.api_tracks_screen

import androidx.lifecycle.ViewModel
import com.music_app.domain.usecase.api_tracks.GetApiTracksByQueryUseCase
import com.music_app.domain.usecase.api_tracks.GetApiTracksUseCase
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
    private val getApiTracksUseCase: GetApiTracksUseCase,
    private val getApiTracksByQueryUseCase: GetApiTracksByQueryUseCase
) : ContainerHost<ApiTracksState, ApiTracksSideEffect>, ViewModel() {

    override val container = container<ApiTracksState, ApiTracksSideEffect>(ApiTracksState())

    fun loadTracks() = intent {
        reduce { state.copy(isLoading = true) }

        val result = getApiTracksUseCase.getTracks()

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

    fun searchTracks(query: String) {
        intent {
            reduce { state.copy(isLoading = true) }

            val result = getApiTracksByQueryUseCase.getTracksByQuery(query)

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
                    val errorMessage = exception.message ?: "Ошибка при поиске"
                    reduce { state.copy(isLoading = false, errorMessage = errorMessage) }
                    postSideEffect(ApiTracksSideEffect.ShowError(errorMessage))
                }
        }
    }

    fun navigate(id: Long) = intent {
        postSideEffect(ApiTracksSideEffect.NavigateTo(id))
    }
}