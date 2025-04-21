package com.music_app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.music_app.ui.models.TrackVO
import com.music_app.ui.theme.LocalCustomColors
import com.music_app.ui.theme.LocalCustomTypography

@Composable
fun TracksListScreenContent(
    isLoading: Boolean,
    tracks: List<TrackVO>,
    placeholderText: String,
    onTrackClick: (Long) -> Unit
) {
    val customColors = LocalCustomColors.current
    val customTypography = LocalCustomTypography.current

    when {
        isLoading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(customColors.bottomNavItem),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = customColors.background)
            }
        }

        tracks.isEmpty() -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(customColors.bottomNavItem),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = placeholderText,
                    style = customTypography.bigDescription.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        else -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(customColors.bottomNavItem),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                items(tracks) { track ->
                    CustomTrack(
                        cover = track.albumCover,
                        title = track.title,
                        artistName = track.artistName,
                        onClick = { onTrackClick(track.id) }
                    )
                }
            }
        }
    }
}
