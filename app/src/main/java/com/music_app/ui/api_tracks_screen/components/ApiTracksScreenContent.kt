package com.music_app.ui.api_tracks_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.music_app.R
import com.music_app.ui.api_tracks_screen.ApiTracksState
import com.music_app.ui.components.CustomSearchBar
import com.music_app.ui.components.TracksListScreenContent
import com.music_app.ui.models.TrackVO
import com.music_app.ui.play_track_screen.PlayTrackSource
import com.music_app.ui.theme.LocalCustomColors

@Composable
fun ApiTracksScreenContent(
    state: ApiTracksState,
    onTrackClick: (Long, PlayTrackSource) -> Unit,
    searchTracks: (String) -> Unit,
    clearSearch: () -> Unit
) {
    val customColors = LocalCustomColors.current
    val trackName = rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(customColors.bottomNavItem)
    ) {
        CustomSearchBar(
            text = trackName,
            hintResId = R.string.search_tracks,
            onSearch = searchTracks,
            onClear = clearSearch
        )

        TracksListScreenContent(
            isLoading = state.isLoading,
            tracks = state.tracks,
            placeholderText = stringResource(id = R.string.no_tracks),
            playTrackSource = PlayTrackSource.FROM_API,
            onTrackClick = onTrackClick
        )
    }
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
        ),
        onTrackClick = { _, _ -> },
        searchTracks = {},
        clearSearch = {}
    )
}