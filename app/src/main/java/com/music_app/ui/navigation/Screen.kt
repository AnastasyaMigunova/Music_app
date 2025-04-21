package com.music_app.ui.navigation

import com.music_app.ui.play_track_screen.PlayTrackSource

sealed class Screen(
    val route: String
) {
    data object ApiTracksTab: Screen(API_TRACKS_TAB)
    data object SavedTracksTab: Screen(SAVED_TRACKS_TAB)

    data object ApiTracksScreen: Screen(ROUTE_API_TRACKS_SCREEN)
    data object SavedTracksScreen: Screen(ROUTE_SAVED_TRACKS_SCREEN)
    data object PlayTrackScreen : Screen(ROUTE_PLAY_TRACK_SCREEN) {
        fun createRoute(id: Long, source: PlayTrackSource): String {
            return "$ROUTE_PLAY_TRACK_SCREEN/$id/${source.name}"
        }
    }

    companion object {
        private const val API_TRACKS_TAB = "api_tracks_tab"
        private const val SAVED_TRACKS_TAB = "saved_tracks_tab"

        private const val ROUTE_API_TRACKS_SCREEN = "api_tracks_screen"
        private const val ROUTE_SAVED_TRACKS_SCREEN = "saved_tracks_screen"
        private const val ROUTE_PLAY_TRACK_SCREEN = "play_track_screen"
    }
}