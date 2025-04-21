package com.music_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.music_app.ui.play_track_screen.PlayTrackScreen
import com.music_app.ui.play_track_screen.PlayTrackSource
import com.music_app.ui.play_track_screen.PlayTrackViewModel

fun NavGraphBuilder.apiTracksNavGraph(
    apiTracksScreen: @Composable () -> Unit,
    playTrackScreen: @Composable (Long, PlayTrackSource) -> Unit
) {
    navigation(
        startDestination = Screen.ApiTracksScreen.route,
        route = Screen.ApiTracksTab.route
    ) {
        composable(Screen.ApiTracksScreen.route) {
            apiTracksScreen()
        }

        composable(
            route = "${Screen.ApiTracksTab.route}/${Screen.PlayTrackScreen.route}/{id}/{source}",
            arguments = listOf(
                navArgument("id") { type = NavType.LongType },
                navArgument("source") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong("id") ?: 0L
            val source = backStackEntry.arguments?.getString("source")?.let {
                PlayTrackSource.valueOf(it)
            } ?: PlayTrackSource.FROM_API


            PlayTrackScreen(
                id = id,
                source = source
            )
        }
    }
}