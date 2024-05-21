package com.chipmango.kmp.uikit.input

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.chipmango.kmp.core.theme.typography.UIKitTypography
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(FormatStringsInDatetimeFormats::class)
@Composable
fun TimeInput(
    time: LocalTime,
    containerColor: Color,
    contentColor: Color,
    contentPadding: PaddingValues = PaddingValues(8.dp),
    shape: Shape = RoundedCornerShape(8.dp),
    textStyle: TextStyle = UIKitTypography.Body1Regular16,
    timePattern: String = "hh:mm a",
    onClick: () -> Unit = {}
) {
    Text(
        text = time.format(LocalTime.Format { byUnicodePattern(timePattern) }),
        style = textStyle,
        modifier = Modifier,
        color = contentColor
    )
}

@Preview
@Composable
fun TimeInputPreview() {
    TimeInput(
        time = LocalTime(8,0),
        containerColor = Color.Gray,
        contentColor = Color.White
    )
}
