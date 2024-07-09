package com.atom.example.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

/**
 * Перечень кастомных текстовых стилей.
 * Для большего количества цветов можно сделать енум
 */
data class AppTypography(
    val header: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        letterSpacing = 0.5.sp,
        textAlign = TextAlign.Center
    ),
    val subtitle1: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.15.sp
    ),
    val subtitle2: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.1.sp
    ),
    val body1: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        letterSpacing = 0.5.sp
    ),
    val body2: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        letterSpacing = 0.25.sp
    ),
    val button: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 1.25.sp
    ),
)