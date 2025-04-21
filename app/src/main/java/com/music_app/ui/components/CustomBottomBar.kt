package com.music_app.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.music_app.ui.navigation.NavigationItem
import com.music_app.ui.theme.LocalCustomColors


@Composable
fun CustomBottomBar(
    modifier: Modifier = Modifier,
    items: List<NavigationItem>,
    selected: (NavigationItem) -> Boolean,
    onClick: (NavigationItem) -> Unit
) {
    val customColors = LocalCustomColors.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(customColors.bottomNavItem)
            .drawBehind {
                val strokeWidth = 2.dp.toPx()
                val y = 0f
                drawLine(
                    color = customColors.backgroundItem,
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidth = strokeWidth
                )
            }
            .padding(vertical = 12.dp, horizontal = 70.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items.forEach { item ->
            BottomNavigationIcon(
                iconId = item.iconId,
                isSelected = selected(item)
            ) { onClick(item) }
        }
    }
}

@Composable
fun BottomNavigationIcon(
    @DrawableRes iconId: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(height = 44.dp, width = 62.dp)
            .background(
                color = if (isSelected) LocalCustomColors.current.backgroundItem else Color.Transparent,
                shape = RoundedCornerShape(20.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null,
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(
                        bounded = false,
                        radius = 32.dp,
                        color = LocalCustomColors.current.bottomNavItem
                    )
                ) { onClick() }
        )
    }
}