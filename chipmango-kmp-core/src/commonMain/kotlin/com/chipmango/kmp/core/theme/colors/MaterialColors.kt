package com.chipmango.kmp.core.theme.colors

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

val md_theme_light_onError = Color(0xFFFFFFFF)
val md_theme_light_error = Color(0xFFB3261E)
val md_theme_light_onBackground = Color(0xFF1C1B1F)
val md_theme_light_background = Color(0xFFFFFBFE)
val md_theme_light_onSurface = Color(0xFF1C1B1F)
val md_theme_light_surface = Color(0xFFFFFFFF)
val md_theme_light_onSecondary = Color(0xFFFFFFFF)
val md_theme_light_secondary = Color(0xFF625B71)
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_primary = Color(0xFF6750A4)

val md_theme_dark_onError = Color(0xFF601410)
val md_theme_dark_error = Color(0xFFF2B8B5)
val md_theme_dark_onBackground = Color(0xFFE6E1E5)
val md_theme_dark_background = Color(0xFF2B2A3A)
val md_theme_dark_onSurface = Color(0xFFE6E1E5)
val md_theme_dark_surface = Color(0xFF1C1B1F)
val md_theme_dark_onSecondary = Color(0xFF332D41)
val md_theme_dark_secondary = Color(0xFFCCC2DC)
val md_theme_dark_onPrimary = Color(0xFFEF6C00)
val md_theme_dark_primary = Color(0xFFFFE0B2)

val MaterialLightColors = Colors(
    onError = md_theme_light_onError,
    error = md_theme_light_error,
    onBackground = md_theme_light_onBackground,
    background = md_theme_light_background,
    onSurface = md_theme_light_onSurface,
    surface = md_theme_light_surface,
    onSecondary = md_theme_light_onSecondary,
    secondary = md_theme_light_secondary,
    onPrimary = md_theme_light_onPrimary,
    primary = md_theme_light_primary,
    primaryVariant = md_theme_light_primary,
    secondaryVariant = md_theme_light_secondary,
    isLight = true
)

val MaterialDarkColors = Colors(
    onError = md_theme_dark_onError,
    error = md_theme_dark_error,
    onBackground = md_theme_dark_onBackground,
    background = md_theme_dark_background,
    onSurface = md_theme_dark_onSurface,
    surface = md_theme_dark_surface,
    onSecondary = md_theme_dark_onSecondary,
    secondary = md_theme_dark_secondary,
    onPrimary = md_theme_dark_onPrimary,
    primary = md_theme_dark_primary,
    primaryVariant = md_theme_dark_primary,
    secondaryVariant = md_theme_dark_secondary,
    isLight = false
)