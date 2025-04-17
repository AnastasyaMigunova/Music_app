package com.music_app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.music_app.R
import com.music_app.ui.theme.LocalCustomColors
import com.music_app.ui.theme.LocalCustomTypography

@Composable
fun CustomTrack(
    cover: String,
    title: String,
    artistName: String
) {
    val customsColors = LocalCustomColors.current
    val customTypography = LocalCustomTypography.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(customsColors.backgroundItem),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomTrackCover(
            modifier = Modifier.size(100.dp),
            cover = cover
        )

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                modifier = Modifier.padding(bottom = 6.dp),
                style = customTypography.bigDescription.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = artistName,
                style = customTypography.description
            )
        }
        
        Spacer(modifier = Modifier.width(140.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_play),
            modifier = Modifier
                .size(46.dp),
            contentDescription = "Play Music",
            tint = customsColors.background
        )
    }
}

@Preview
@Composable
fun PreviewCustomTrack() {
    CustomTrack(
        cover = "example image",
        title = "Track Title",
        artistName = "Artist Name"
    )
}