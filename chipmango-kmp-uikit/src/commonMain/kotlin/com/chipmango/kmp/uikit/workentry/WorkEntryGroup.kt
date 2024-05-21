package com.chipmango.kmp.uikit.workentry

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.chipmango.kmp.core.theme.typography.UIKitTypography
import com.chipmango.kmp.platform.DateTimeFormatter.kmpFormat
import com.chipmango.kmp.theme.themeColors
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@OptIn(FormatStringsInDatetimeFormats::class)
@Composable
fun WorkEntryGroup(
    date: LocalDate,
    numberOfEntry: Int,
    numberOfEntryText: String,
    subtitle: String,
    title: String,
    duration: Duration,
    deleteMode: Boolean,
    durationFormatter: @Composable (Duration) -> String = { formatDuration(it) },
    contentPadding: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
    emptyContainerColor: Color = themeColors().background.Stronger,
    containerColor: Color = Color.Transparent,
    colors: List<Color>,
    dateTextColor: Color = themeColors().text.Stronger,
    firstLineColor: Color = themeColors().text.Stronger,
    secondLineColor: Color = themeColors().text.Normal,
    shape: Shape = RectangleShape,
    content: @Composable() (AnimatedVisibilityScope.() -> Unit) = {},
    onCreateNewWorkEntry: (LocalDate) -> Unit,
    onDeleteAllWorkEntries: (LocalDate) -> Unit,
    onClick: () -> Unit
) {
    var expand by remember {
        mutableStateOf(false)
    }
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(
                    color = containerColor,
                    shape = shape
                )
                .clickable {
                    if (deleteMode.not()) {
                        when (numberOfEntry) {
                            0 -> onCreateNewWorkEntry(date)
                            1 -> onClick()
                            else -> expand = !expand
                        }
                    } else {
                        if (numberOfEntry > 1) expand = !expand
                    }
                }
                .padding(contentPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AnimatedVisibility(deleteMode && numberOfEntry > 0) {
                IconButton(
                    modifier = Modifier.padding(end = 12.dp),
                    onClick = { onDeleteAllWorkEntries(date) }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Delete,
                        contentDescription = null,
                        tint = themeColors().negative.Normal
                    )
                }
            }

            WorkEntryColorBar(
                modifier = Modifier.fillMaxHeight(),
                colors = colors,
                width = 4.dp,
                emptyColor = themeColors().divider.Strong
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.width(30.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = date.kmpFormat("EEE").uppercase(),
                    style = UIKitTypography.Caption1Medium12,
                    color = dateTextColor
                )

                Text(
                    text = date.kmpFormat("dd"),
                    style = UIKitTypography.Body3Medium13,
                    color = dateTextColor
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            if (numberOfEntry <= 0) {
                WorkEntryEmpty(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = emptyContainerColor
                )
            } else {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
                ) {
                    Text(
                        text = title,
                        style = UIKitTypography.Body2Medium14,
                        color = firstLineColor,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = subtitle,
                        style = UIKitTypography.Body2Regular14,
                        color = secondLineColor,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
                ) {
                    Text(
                        text = durationFormatter(duration),
                        style = UIKitTypography.Body2Medium14,
                        color = firstLineColor
                    )

                    Text(
                        text = numberOfEntryText,
                        style = UIKitTypography.Body2Regular14,
                        color = secondLineColor
                    )
                }
            }
        }

        AnimatedVisibility(visible = expand) {
            content()
        }
    }
}

@Composable
fun WorkEntryColorBar(
    modifier: Modifier,
    colors: List<Color>,
    emptyColor: Color,
    width: Dp,
    shape: Shape = RoundedCornerShape(width)
) {
    Column(
        modifier = modifier.width(width),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        if (colors.isEmpty()) {
            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = emptyColor, shape = shape)
            )
        } else {
            colors.forEach { color ->
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(color = color, shape = shape)
                )
            }
        }
    }
}

private fun formatDuration(duration: Duration): String {
    val hours = duration.inWholeMinutes.div(60)
    val minutes = duration.inWholeMinutes.rem(60)
    return buildString {
        if (hours > 0) append("$hours h ")
        if (minutes > 0) append("$minutes m")
    }
}

@Composable
private fun WorkEntryEmpty(modifier: Modifier, containerColor: Color) {
    Spacer(modifier = modifier.background(containerColor))
}

@Preview
@Composable
fun WorkEntryGroupPreview() {
    WorkEntryGroup(
        date = Clock.System.now().toLocalDateTime(TimeZone.UTC).date,
        numberOfEntry = 2,
        subtitle = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec odio nec eros ultricies tincidunt.",
        title = "08:00 AM - 05:00 PM",
        duration = 8.toDuration(DurationUnit.HOURS),
        emptyContainerColor = Color.Gray,
        containerColor = Color.Black.copy(alpha = 0.5f),
        colors = listOf(Color.Red, Color.Blue),
        dateTextColor = Color.White,
        firstLineColor = Color.White,
        secondLineColor = Color.White.copy(alpha = 0.5f),
        onCreateNewWorkEntry = {},
        onClick = {},
        onDeleteAllWorkEntries = {},
        deleteMode = false,
        numberOfEntryText = "2 entries"
    )
}

@Preview
@Composable
private fun PreviewColorDivider() {
    WorkEntryColorBar(
        modifier = Modifier.height(60.dp),
        colors = listOf(Color.Red, Color.Green, Color.Blue),
        emptyColor = Color.Gray,
        width = 4.dp
    )
}