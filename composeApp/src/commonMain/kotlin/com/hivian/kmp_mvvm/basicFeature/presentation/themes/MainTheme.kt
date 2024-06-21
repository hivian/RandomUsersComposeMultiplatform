package com.hivian.kmp_mvvm.basicFeature.presentation.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val colorGreyLight = Color(0xFFE1E2EB)
val colorGreyDark = Color(0xFF45474E)

data class CustomColorPalette(
    val toolbarBackgroundColor: Color = Color.Unspecified,
    val toolbarContentColor: Color = Color.Unspecified,
)

val LocalCustomColorPalette = staticCompositionLocalOf { CustomColorPalette() }

val lightCustomColorPalette = CustomColorPalette(
    toolbarBackgroundColor = lightColorScheme().background,
    toolbarContentColor = Color.Black,
)

val darkCustomColorPalette = CustomColorPalette(
    toolbarBackgroundColor = darkColorScheme().background,
    toolbarContentColor = Color.White
)

fun lightColorScheme() = lightColors().apply {
    return copy(
        primary = Color.Black,
        surface = colorGreyLight
    )
}

fun darkColorScheme() = darkColors().apply {
    return copy(
        primary = Color.White,
        surface = colorGreyDark
    )
}

@Composable
fun MainTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> darkColorScheme()
        else -> lightColorScheme()
    }
    val customColorsPalette = when {
        darkTheme -> darkCustomColorPalette
        else -> lightCustomColorPalette
    }

    CompositionLocalProvider(LocalCustomColorPalette provides customColorsPalette) {
        MaterialTheme(
            colors = colorScheme,
            typography = Typography,
            content = content
        )
    }

}