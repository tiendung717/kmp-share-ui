package com.chipmango.kmp.uikit.picker

import androidx.compose.runtime.Composable
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlin.time.Duration

@Composable
expect fun rememberDatePicker(
    currentDate: LocalDate,
    minDate: LocalDate? = null,
    maxDate: LocalDate? = null,
    onDismiss: () -> Unit = {},
    onCancel: () -> Unit = {},
    onDateSelected: (LocalDate) -> Unit
): DatePickerController

@Composable
expect fun rememberDateRangePicker(
    minDate: LocalDate? = null,
    maxDate: LocalDate? = null,
    onDismiss: () -> Unit = {},
    onCancel: () -> Unit = {},
    onDateSelected: (LocalDate, LocalDate) -> Unit
) : DateRangePickerController

@Composable
expect fun rememberTimePicker(
    currentTime: LocalTime,
    onDismiss: () -> Unit = {},
    onCancel: () -> Unit = {},
    onTimeSelected: (LocalTime) -> Unit
) : TimePickerController

@Composable
expect fun rememberDurationPicker(
    currentDuration: Duration,
    onDismiss: () -> Unit = {},
    onCancel: () -> Unit = {},
    onDurationSelected: (Duration) -> Unit
) : TimePickerController