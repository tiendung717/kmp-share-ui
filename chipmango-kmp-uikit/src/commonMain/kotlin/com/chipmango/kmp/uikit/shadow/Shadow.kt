package com.chipmango.kmp.uikit.shadow

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

expect fun Modifier.dropShadow(
    color: Color = Color(0xFF29293D).copy(0.07f),
    offsetX: Dp = 0.dp,
    offsetY: Dp = 7.dp,
    blurRadius: Dp = 20.dp
): Modifier