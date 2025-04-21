package com.music_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation

fun NavGraphBuilder.apiTracksNavGraph(
    apiTracksScreen: @Composable () -> Unit,
    playTrackScreen: @Composable (Long) -> Unit
) {
    navigation(
        startDestination = Screen.ApiTracksScreen.route,
        route = Screen.ApiTracksTab.route
    ) {

        composable(Screen.ApiTracksScreen.route) {
            apiTracksScreen()
        }
    }
}