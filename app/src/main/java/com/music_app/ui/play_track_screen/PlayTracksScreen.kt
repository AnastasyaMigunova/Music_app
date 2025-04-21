package com.music_app.ui.play_track_screen

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.util.UnstableApi
import com.music_app.data.media_service.MusicPlayerService
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
    val track = state.track ?: return

    val serviceIntent = Intent(context, MusicPlayerService::class.java).apply {
        putExtra("track", track)
    }
    context.startService(serviceIntent)

    PlayTrackScreenContent(
        state = state,
        currentPosition = state.currentPosition.toFloat(),
        duration = state.duration,
        isPlaying = state.isPlaying,
        onSeek = { },
        onPlayPauseClick = {
            if (state.isPlaying) {
                val pauseIntent = Intent(context, MusicPlayerService::class.java).apply {
                    putExtra("action", "pause")
                }
                context.startService(pauseIntent)
            } else {
                val playIntent = Intent(context, MusicPlayerService::class.java).apply {
                    putExtra("action", "play")
                }
                context.startService(playIntent)
            }
        },
        onNextClick = {  },
        onPreviousClick = {  }
    )
}