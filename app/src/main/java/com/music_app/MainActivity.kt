package com.music_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.music_app.ui.api_tracks_screen.ApiTracksScreen
import com.music_app.ui.components.CustomBottomBar
import com.music_app.ui.navigation.NavGraph
import com.music_app.ui.navigation.NavigationItem
import com.music_app.ui.navigation.Screen
import com.music_app.ui.navigation.rememberNavigationState
import com.music_app.ui.play_track_screen.PlayTrackScreen
import com.music_app.ui.play_track_screen.PlayTrackSource
import com.music_app.ui.saved_tracks_screen.SavedTracksScreen
import com.music_app.ui.theme.Music_appTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Music_appTheme {
                val navigationState = rememberNavigationState()
                val isBottomBarVisible = remember { mutableStateOf(true) }

                Scaffold(
                    containerColor = Color.Transparent,
                    modifier = Modifier
                        .padding(
                            bottom = WindowInsets.navigationBars.asPaddingValues()
                                .calculateBottomPadding()
                        ),
                    bottomBar = {
                        val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()

                        val items = listOf(
                            NavigationItem.ApiTracks,
                            NavigationItem.SavedTracks
                        )

                        AnimatedVisibility(
                            visible = isBottomBarVisible.value,
                            enter = fadeIn() + expandVertically(),
                            exit = fadeOut() + shrinkVertically(),
                        ) {
                            CustomBottomBar(
                                items = items,
                                selected = { navItem ->
                                    navBackStackEntry?.destination?.hierarchy?.any {
                                        it.route == navItem.screen.route
                                    } ?: false
                                }
                            ) {
                                navigationState.navigateTo(it.screen.route)
                            }
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavGraph(
                            navHostController = navigationState.navHostController,
                            apiTracksScreenContent = {
                                ApiTracksScreen(
                                    navigate = { id: Long, source: PlayTrackSource ->
                                        navigationState.navigateAndClearTo(
                                            "${Screen.ApiTracksTab.route}/${Screen.PlayTrackScreen.createRoute(id, source)}"
                                        )
                                    }
                                )
                            },
                            savedTracksScreenContent = {
                                SavedTracksScreen(
                                    navigate = { id: Long, source: PlayTrackSource ->
                                        navigationState.navigateAndClearTo(
                                            "${Screen.SavedTracksTab.route}/${Screen.PlayTrackScreen.createRoute(id, source)}"
                                        )
                                    }
                                )
                            },
                            playTrackScreenContent = { id, source ->
                                PlayTrackScreen(
                                    id = id,
                                    source = source
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}