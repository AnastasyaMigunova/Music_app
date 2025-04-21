package com.music_app.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.music_app.ui.play_track_screen.PlayTrackSource

private const val TRANSITION_DURATION = 300

@Composable
fun NavGraph(
    navHostController: NavHostController,
    apiTracksScreenContent: @Composable () -> Unit,
    savedTracksScreenContent: @Composable () -> Unit,
    playTrackScreenContent: @Composable (Long, PlayTrackSource) -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.ApiTracksTab.route,
        enterTransition = { fadeIn(tween(TRANSITION_DURATION)) },
        exitTransition = { fadeOut(tween(TRANSITION_DURATION)) },
        popExitTransition = { fadeOut(tween(TRANSITION_DURATION)) },
        popEnterTransition = { fadeIn(tween(TRANSITION_DURATION)) }
    ) {
        apiTracksNavGraph(
            apiTracksScreen = apiTracksScreenContent,
            playTrackScreen = playTrackScreenContent
        )
        savedTracksNavGraph(
            savedTracksScreen = savedTracksScreenContent,
            playTrackScreen = playTrackScreenContent
        )
    }
}