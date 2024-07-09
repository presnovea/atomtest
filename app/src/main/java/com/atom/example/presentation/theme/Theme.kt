package com.atom.example.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf


/**
 * Кастомная тема приложения
 *
 * @param darkTheme включена ли темная тема
 * @param content контент
 */
@Composable
fun AtomTestTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColors provides ColorThemeMap(darkTheme),
        LocalTypography provides AppTheme.typography,
    ) {
        content()
    }
}

internal val LocalColors = staticCompositionLocalOf { LightColorScheme }
internal val LocalTypography = staticCompositionLocalOf { AppTypography() }

/**
 * Обжект для доступа к компонентам темы
 *
 * @property colors Цвета темы
 * @property typography Шрифты темы
 */
object AppTheme {
    val colors: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}