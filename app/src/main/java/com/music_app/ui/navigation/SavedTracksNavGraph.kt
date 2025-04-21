package com.music_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation

fun NavGraphBuilder.savedTracksNavGraph(
    savedTracksScreen: @Composable () -> Unit,
    playTrackScreen: @Composable (Long) -> Unit
) {
    navigation(
        startDestination = Screen.SavedTracksScreen.route,
        route = Screen.SavedTracksTab.route
    ) {

        composable(Screen.SavedTracksScreen.route) {
            savedTracksScreen()
        }

    }
}