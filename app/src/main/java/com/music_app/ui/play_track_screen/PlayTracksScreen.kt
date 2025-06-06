package com.music_app.ui.play_track_screen

import android.util.Log
import android.widget.Toast
import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.util.UnstableApi
import com.music_app.ui.play_track_screen.components.PlayTrackScreenContent
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.compose.collectAsState

@OptIn(UnstableApi::class)
@Composable
fun PlayTrackScreen(
    id: Long,
    source: PlayTrackSource,
    viewModel: PlayTrackViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState().value
    val context = LocalContext.current

    LaunchedEffect(id, source) {
        viewModel.loadTrack(id, source)
    }

    LaunchedEffect(Unit) {
        viewModel.container.sideEffectFlow.collectLatest { sideEffect ->
            when (sideEffect) {
                is PlayTrackSideEffect.ShowError -> {
                    Log.e("PlayTrackScreen", "Error: ${sideEffect.message}")
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    PlayTrackScreenContent(
        state = state,
        currentPosition = state.currentPosition.toFloat(),
        duration = state.duration,
        isPlaying = state.isPlaying,
        onSeek = { },
        onPlayPauseClick = {

        },
        onNextClick = {  },
        onPreviousClick = {  }
    )
}