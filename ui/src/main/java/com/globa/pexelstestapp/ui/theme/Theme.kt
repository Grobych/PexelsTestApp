package com.globa.pexelstestapp.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    background = Dark_Black,
    onBackground = White,
    surface = Dark_Gray,
    onSurface = Dark_White,
    primary = Dark_Red,
    onPrimary = Dark_White,
    secondary = Dark_DarkGray,
    tertiary = Dark_Red
)

private val LightColorScheme = lightColorScheme(
    background = White,
    onBackground = Black,
    surface = Gray,
    onSurface = Black,
    primary = Red,
    onPrimary = White,
    secondary = DarkGray,
    tertiary = Red
)

@Composable
fun PexelsTestAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}