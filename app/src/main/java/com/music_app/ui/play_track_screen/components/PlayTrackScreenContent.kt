package com.music_app.ui.play_track_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.music_app.R
import com.music_app.ui.components.CustomTrackCover
import com.music_app.ui.models.TrackVO
import com.music_app.ui.play_track_screen.PlayTrackState
import com.music_app.ui.theme.LocalCustomColors
import com.music_app.ui.theme.LocalCustomTypography

@Composable
fun PlayTrackScreenContent(
    state: PlayTrackState,
    currentPosition: Float,
    duration: Long,
    isPlaying: Boolean,
    onSeek: (Float) -> Unit,
    onPlayPauseClick: () -> Unit,
    onNextClick: () -> Unit,
    onPreviousClick: () -> Unit,
) {
    val customColors = LocalCustomColors.current
    val customTypography = LocalCustomTypography.current

    val track = state.track ?: return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(customColors.backgroundItem)
            .padding(horizontal = 16.dp),
    ) {
        CustomTrackCover(
            modifier = Modifier
                .padding(vertical = 60.dp)
                .size(300.dp)
                .align(Alignment.CenterHorizontally),
            cover = track.albumCover
        )

        Text(
            text = track.title,
            modifier = Modifier.padding(bottom = 8.dp),
            style = customTypography.bigDescription.copy(
                fontWeight = FontWeight.Bold
            )
        )

        Text(
            text = track.artistName,
            modifier = Modifier.padding(bottom = 20.dp),
            style = customTypography.description.copy(
                fontWeight = FontWeight.Bold
            )
        )

        Slider(
            value = currentPosition,
            onValueChange = onSeek,
            valueRange = 0f..duration.toFloat(),
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(formatTime(currentPosition.toLong()))
            Text(formatTime(duration))
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = onPreviousClick) {
                Icon(painterResource(R.drawable.ic_previous), contentDescription = "Previous")
            }
            IconButton(onClick = onPlayPauseClick) {
                Icon(
                    if (isPlaying) painterResource(R.drawable.ic_pause) else painterResource(R.drawable.ic_play),
                    contentDescription = if (isPlaying) "Pause" else "Play"
                )
            }
            IconButton(onClick = onNextClick) {
                Icon(painterResource(R.drawable.ic_next), contentDescription = "Next")
            }
        }
    }
}

private fun formatTime(seconds: Long): String {
    val minutesPart = seconds / 60
    val secondsPart = seconds % 60
    return "%02d:%02d".format(minutesPart, secondsPart)
}

@Preview(showBackground = true)
@Composable
fun PreviewPlayTrackScreenContent() {
    PlayTrackScreenContent(
        state = PlayTrackState(
            isLoading = false,
            track = TrackVO(
                id = 5634324,
                title = "Gimme More",
                previewUrl = "",
                duration = 180_000,
                artistName = "Britney Spears",
                artistPicture = "",
                albumTitle = "Blackout",
                albumCover = ""
            ),
            errorMessage = null
        ),
        currentPosition = 45_000f, // 45 сек
        duration = 180_000,
        isPlaying = true,
        onSeek = {},
        onPlayPauseClick = {},
        onNextClick = {},
        onPreviousClick = {}
    )
}