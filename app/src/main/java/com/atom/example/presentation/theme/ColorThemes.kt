package com.atom.example.presentation.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

internal val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

internal val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

/**
 * Функция провайдинга кастомных цветов в тему
 */
fun ColorThemeMap(isDarkTheme: Boolean = false): ColorScheme {
    return if(isDarkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }
}