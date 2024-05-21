package com.chipmango.kmp.uikit.workentry

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.chipmango.kmp.core.theme.typography.UIKitTypography
import com.chipmango.kmp.theme.themeColors
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@OptIn(FormatStringsInDatetimeFormats::class)
@Composable
fun WorkEntryItem(
    modifier: Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
    firstLineColor: Color = themeColors().text.Stronger,
    secondLineColor: Color = themeColors().text.Normal,
    shape: Shape = RectangleShape,
    containerColor: Color = themeColors().background.Stronger,
    workType: String,
    startTime: LocalTime,
    endTime: LocalTime,
    duration: Duration,
    color: Color,
    deleteMode: Boolean,
    timeFormatter: @Composable (LocalTime) -> String = {
        val timeFormat = LocalTime.Format { byUnicodePattern("hh:mm a") }
        it.format(timeFormat)
    },
    durationFormatter: @Composable (Duration) -> String = { it.toString() },
    onDeleteWorkEntry: () -> Unit,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .background(
                color = containerColor,
                shape = shape
            )
            .clickable { onClick() }
            .padding(contentPadding),
        verticalAlignment = Alignment.Bottom
    ) {
        AnimatedVisibility(deleteMode) {
            IconButton(
                modifier = Modifier.padding(end = 12.dp),
                onClick = onDeleteWorkEntry
            ) {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = null,
                    tint = themeColors().negative.Normal
                )
            }
        }

        Spacer(
            modifier = Modifier
                .width(4.dp)
                .height(40.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(color, RoundedCornerShape(4.dp))
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
        ) {
            Text(
                text = workType,
                style = UIKitTypography.Body3Regular13,
                color = secondLineColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = buildString {
                    append(timeFormatter(startTime))
                    append(" - ")
                    append(timeFormatter(endTime))
                },
                style = UIKitTypography.Body2Medium14,
                color = firstLineColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = durationFormatter(duration),
            style = UIKitTypography.Body2Medium14,
            color = firstLineColor
        )


    }
}

@Preview
@Composable
fun WorkEntryItemPreview() {
    WorkEntryItem(
        modifier = Modifier,
        firstLineColor = Color.White.copy(alpha = 0.5f),
        secondLineColor = Color.White,
        shape = RoundedCornerShape(4.dp),
        containerColor = Color.Black.copy(alpha = 0.5f),
        workType = "Work Type",
        startTime = LocalTime(9, 0),
        endTime = LocalTime(17, 0),
        color = Color.Blue,
        duration = 8.toDuration(DurationUnit.HOURS),
        deleteMode = false,
        onClick = {},
        onDeleteWorkEntry = {}
    )
}