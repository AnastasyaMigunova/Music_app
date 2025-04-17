package com.music_app.ui.api_tracks_screen.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.music_app.ui.api_tracks_screen.ApiTracksState
import com.music_app.ui.models.TrackVO

@Composable
fun ApiTracksScreenContent(
    state: ApiTracksState
) {

}

@Preview
@Composable
fun PreviewApiTracksScreenContent() {
    ApiTracksScreenContent(
        state = ApiTracksState(
            isLoading = false,
            tracks = listOf(
                TrackVO(
                    id = 3435343534,
                    title = "Shape of You",
                    artistName = "Ed Sheeran",
                    albumTitle = "Divide",
                    duration = 240,
                    previewUrl = "",
                    albumCover = "",
                    artistPicture = ""
                )
            ),
            errorMessage = null
        )
    )
}