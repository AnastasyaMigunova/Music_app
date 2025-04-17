package com.music_app.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.music_app.R

@Composable
fun CustomTrackCover(
    modifier: Modifier = Modifier,
    cover: String
) {
    AsyncImage(
        model = cover,
        contentDescription = "Track Cover",
        modifier = modifier
            .clip(RoundedCornerShape(8.dp)),
        error = painterResource(R.drawable.default_track_cover)
    )
}