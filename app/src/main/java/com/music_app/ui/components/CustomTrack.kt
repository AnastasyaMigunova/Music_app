package com.music_app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    artistName: String,
    onClick: () -> Unit
) {
    val customsColors = LocalCustomColors.current
    val customTypography = LocalCustomTypography.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(horizontal = 4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(customsColors.backgroundItem)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomTrackCover(
            modifier = Modifier
                .size(60.dp)
                .padding(start = 8.dp),
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
                modifier = Modifier
                    .width(250.dp)
                    .padding(bottom = 6.dp),
                style = customTypography.description.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = artistName,
                style = customTypography.description
            )
        }
    }
}

@Preview
@Composable
fun PreviewCustomTrack() {
    CustomTrack(
        cover = "example image",
        title = "Track Title",
        artistName = "Artist Name",
        onClick = {}
    )
}