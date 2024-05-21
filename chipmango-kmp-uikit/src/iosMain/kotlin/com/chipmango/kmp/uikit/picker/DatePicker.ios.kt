package com.chipmango.kmp.uikit.picker

import androidx.compose.runtime.Composable
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlin.time.Duration

@Composable
actual fun rememberDatePicker(
    currentDate: LocalDate,
    minDate: LocalDate?,
    maxDate: LocalDate?,
    onDismiss: () -> Unit,
    onCancel: () -> Unit,
    onDateSelected: (LocalDate) -> Unit
): DatePickerController {
    throw IllegalStateException("DatePicker is not implemented for iOS")
}

@Composable
actual fun rememberDateRangePicker(
    minDate: LocalDate?,
    maxDate: LocalDate?,
    onDismiss: () -> Unit,
    onCancel: () -> Unit,
    onDateSelected: (LocalDate, LocalDate) -> Unit
): DateRangePickerController {
    throw IllegalStateException("DatePicker is not implemented for iOS")
}

@Composable
actual fun rememberTimePicker(
    currentTime: LocalTime,
    onDismiss: () -> Unit,
    onCancel: () -> Unit,
    onTimeSelected: (LocalTime) -> Unit
): TimePickerController {
    throw IllegalStateException("DatePicker is not implemented for iOS")
}

@Composable
actual fun rememberDurationPicker(
    currentDuration: Duration,
    onDismiss: () -> Unit,
    onCancel: () -> Unit,
    onDurationSelected: (Duration) -> Unit
): TimePickerController {
    throw IllegalStateException("DatePicker is not implemented for iOS")
}