package com.music_app.ui.api_tracks_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.music_app.ui.api_tracks_screen.components.ApiTracksScreenContent
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun ApiTracksScreen(
    navigate: (Long) -> Unit,
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
                is ApiTracksSideEffect.NavigateTo -> navigate(sideEffect.id)
                is ApiTracksSideEffect.ShowError -> {
                    Log.e("ApiTracksScreen", "Error: ${sideEffect.message}")
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    ApiTracksScreenContent(
        state = state,
        onTrackClick = { id -> viewModel.navigate(id) },
        searchTracks = { query -> viewModel.searchTracks(query) },
        clearSearch = { viewModel.loadTracks() }
    )
}