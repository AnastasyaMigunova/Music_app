package com.music_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.music_app.ui.play_track_screen.PlayTrackSource

fun NavGraphBuilder.savedTracksNavGraph(
    savedTracksScreen: @Composable () -> Unit,
    playTrackScreen: @Composable (Long, PlayTrackSource) -> Unit
) {
    navigation(
        startDestination = Screen.SavedTracksScreen.route,
        route = Screen.SavedTracksTab.route
    ) {

        composable(Screen.SavedTracksScreen.route) {
            savedTracksScreen()
        }

        composable(
            route = "${Screen.SavedTracksTab.route}/${Screen.PlayTrackScreen.route}/{id}/{source}",
            arguments = listOf(
                navArgument("id") { type = NavType.LongType },
                navArgument("source") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong("id") ?: 0L
            val sourceString = backStackEntry.arguments?.getString("source") ?: PlayTrackSource.FROM_SAVED.name
            val source = PlayTrackSource.valueOf(sourceString)
            playTrackScreen(id, source)
        }
    }
}