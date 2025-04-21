package com.music_app.ui.saved_tracks_screen

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.music_app.ui.saved_tracks_screen.components.SavedTracksScreenContent
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun SavedTracksScreen(
    navigate: (Long) -> Unit,
    viewModel: SavedTracksViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState().value
    val context = LocalContext.current

    val permissionToRequest = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        Manifest.permission.READ_MEDIA_AUDIO
    } else {
        Manifest.permission.READ_EXTERNAL_STORAGE
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            viewModel.loadTracks()
        } else {
            Toast.makeText(context, "Разрешение не предоставлено", Toast.LENGTH_LONG).show()
        }
    }

    val permissionStatus = ContextCompat.checkSelfPermission(context, permissionToRequest)

    LaunchedEffect(Unit) {
        if (permissionStatus != PackageManager.PERMISSION_GRANTED) {
            permissionLauncher.launch(permissionToRequest)
        } else {
            viewModel.loadTracks()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.container.sideEffectFlow.collectLatest { sideEffect ->
            when (sideEffect) {
                is SavedTracksSideEffect.NavigateTo -> navigate(sideEffect.id)
                is SavedTracksSideEffect.ShowError -> {
                    Log.e("SavedTracksScreen", "Error: ${sideEffect.message}")
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    SavedTracksScreenContent(
        state = state,
        onTrackClick = { id -> viewModel.navigate(id) },
        searchTracks = { query -> viewModel.searchTracks(query) },
        clearSearch = { viewModel.loadTracks() }
    )
}