package com.sierra.common.ui.theme

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Colors.colorPrimaryDark,
    secondary = Colors.colorSecondaryDark,
    background = Colors.colorBackgroundDark,
    surface = Colors.colorRippleDark,
)

private val LightColorPalette = lightColors(
    primary = Colors.colorPrimary,
    secondary = Colors.colorSecondary,
    background = Colors.colorBackground,
    surface = Colors.colorRipple,
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography(
            primary = colors.primary,
            secondary = colors.secondary,
        ),
        shapes = Shapes,
        content = content
    )
}

fun ComponentActivity.setThemedContent(
    content: @Composable () -> Unit
) {
    setContent {
        AppTheme {
            content()
        }
    }
}