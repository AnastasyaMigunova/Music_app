package com.music_app.ui.api_tracks_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.music_app.ui.api_tracks_screen.components.ApiTracksScreenContent
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun ApiTracksScreen(
    viewModel: ApiTracksViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState().value
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.loadTracks()

        viewModel.container.sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                is ApiTracksSideEffect.NavigateTo -> {
                }

                is ApiTracksSideEffect.ShowError -> {
                    Log.e("ApiTracksScreen", "Error: ${sideEffect.message}")
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    ApiTracksScreenContent(
        state = state
    )
}