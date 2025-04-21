package com.music_app.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class CustomTypography(
    val bigTitle: TextStyle = TextStyle(
        fontSize = 32.sp,
        lineHeight = 38.sp,
        fontWeight = FontWeight.Bold
    ),
    val title: TextStyle = TextStyle(
        fontSize = 28.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.Bold
    ),
    val bigDescription: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 18.sp
    ),
    val description: TextStyle = TextStyle(
        fontSize = 14.sp,
        lineHeight = 16.sp
    )
)

val LocalCustomTypography = staticCompositionLocalOf { CustomTypography() }