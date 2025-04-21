package com.music_app.ui.navigation

import androidx.annotation.DrawableRes
import com.music_app.R

sealed class NavigationItem(
    val screen: Screen,
    @DrawableRes val iconId: Int
) {
    data object ApiTracks: NavigationItem(
        screen = Screen.ApiTracksScreen,
        iconId = R.drawable.ic_api_tracks
    )

    data object SavedTracks: NavigationItem(
        screen = Screen.SavedTracksScreen,
        iconId = R.drawable.ic_saved_tracks
    )
}