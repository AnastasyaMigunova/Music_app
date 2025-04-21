package com.music_app.ui.api_tracks_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.music_app.ui.api_tracks_screen.components.ApiTracksScreenContent
import com.music_app.ui.play_track_screen.PlayTrackSource
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun ApiTracksScreen(
    navigate: (Long, PlayTrackSource) -> Unit,
    viewModel: ApiTracksViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState().value
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.loadTracks()
    }

    LaunchedEffect(Unit) {
        viewModel.container.sideEffectFlow.collectLatest { sideEffect ->
            when (sideEffect) {
                is ApiTracksSideEffect.NavigateTo -> navigate(sideEffect.id, sideEffect.playTrackSource)
                is ApiTracksSideEffect.ShowError -> {
                    Log.e("ApiTracksScreen", "Error: ${sideEffect.message}")
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    ApiTracksScreenContent(
        state = state,
        onTrackClick = { id, playerTrackSource -> viewModel.navigate(id, playerTrackSource) },
        searchTracks = { query -> viewModel.searchTracks(query) },
        clearSearch = { viewModel.loadTracks() }
    )
}