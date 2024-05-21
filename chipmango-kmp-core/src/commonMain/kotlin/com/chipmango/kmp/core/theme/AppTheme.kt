package com.chipmango.kmp.core.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.chipmango.kmp.core.theme.colors.LocalColors
import com.chipmango.kmp.core.theme.colors.MaterialDarkColors
import com.chipmango.kmp.core.theme.colors.MaterialLightColors
import com.chipmango.kmp.core.theme.colors.ThemeColor

@Composable
fun AppTheme(
    useDarkTheme: Boolean,
    themeColors: ThemeColor,
    content: @Composable () -> Unit
) {
    val colors by remember(useDarkTheme) {
        derivedStateOf {
            if (useDarkTheme) themeColors.dark else themeColors.light
        }
    }

    CompositionLocalProvider(LocalColors provides colors) {
        MaterialTheme(
            colors =  if (useDarkTheme) MaterialDarkColors else MaterialLightColors,
            content = {
                Surface(
                    content = content
                )
            }
        )
    }
}