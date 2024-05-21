package com.chipmango.kmp.uikit.picker

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ComponentActivity
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.CompositeDateValidator
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@Composable
actual fun rememberDatePicker(
    currentDate: LocalDate,
    minDate: LocalDate?,
    maxDate: LocalDate?,
    onDismiss: () -> Unit,
    onCancel: () -> Unit,
    onDateSelected: (LocalDate) -> Unit
): DatePickerController {
    val activity = LocalContext.current as AppCompatActivity
    val constraints = if (minDate != null || maxDate != null) {
        val startAt = minDate?.atStartOfDayIn(TimeZone.UTC)?.toEpochMilliseconds()
        val endAt = maxDate?.atStartOfDayIn(TimeZone.UTC)?.toEpochMilliseconds()

        val validators = mutableListOf<CalendarConstraints.DateValidator>()
        startAt?.let {
            validators.add(DateValidatorPointForward.from(it))
        }
        endAt?.let {
            validators.add(DateValidatorPointBackward.before(it))
        }
        CalendarConstraints.Builder()
            .setValidator(CompositeDateValidator.allOf(validators))
            .build()
    } else null

    val picker = MaterialDatePicker.Builder.datePicker()
        .setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR)
        .setPositiveButtonText("Select")
        .setNegativeButtonText("Cancel")
        .setCalendarConstraints(constraints)
        .setSelection(currentDate.atStartOfDayIn(TimeZone.UTC).toEpochMilliseconds())
        .build()
        .apply {
            addOnDismissListener {
                onDismiss()
            }
            addOnCancelListener {
                onCancel()
            }
            addOnPositiveButtonClickListener { epochMilliseconds ->
                val date = LocalDate.fromEpochDays((epochMilliseconds / 86400000).toInt())
                onDateSelected(date)
            }
        }

    return DatePickerController(activity, picker)
}

@Composable
actual fun rememberDateRangePicker(
    minDate: LocalDate?,
    maxDate: LocalDate?,
    onDismiss: () -> Unit,
    onCancel: () -> Unit,
    onDateSelected: (LocalDate, LocalDate) -> Unit
): DateRangePickerController {
    val activity = LocalContext.current as AppCompatActivity
    val constraints = if (minDate != null || maxDate != null) {
        val startAt = minDate?.atStartOfDayIn(TimeZone.UTC)?.toEpochMilliseconds()
        val endAt = maxDate?.atStartOfDayIn(TimeZone.UTC)?.toEpochMilliseconds()

        val validators = mutableListOf<CalendarConstraints.DateValidator>()
        startAt?.let {
            validators.add(DateValidatorPointForward.from(it))
        }
        endAt?.let {
            validators.add(DateValidatorPointBackward.before(it))
        }
        CalendarConstraints.Builder()
            .setValidator(CompositeDateValidator.allOf(validators))
            .build()
    } else null

    val picker = MaterialDatePicker.Builder.dateRangePicker()
        .setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR)
        .setPositiveButtonText("Select")
        .setNegativeButtonText("Cancel")
        .setCalendarConstraints(constraints)
        .build()
        .apply {
            addOnDismissListener {
                onDismiss()
            }
            addOnCancelListener {
                onCancel()
            }
            addOnPositiveButtonClickListener { selection ->
                val startDate = LocalDate.fromEpochDays((selection.first / 86400000).toInt())
                val endDate = LocalDate.fromEpochDays((selection.second / 86400000).toInt())
                onDateSelected(startDate, endDate)
            }
        }

    return DateRangePickerController(activity, picker)
}

@Composable
actual fun rememberTimePicker(
    currentTime: LocalTime,
    onDismiss: () -> Unit,
    onCancel: () -> Unit,
    onTimeSelected: (LocalTime) -> Unit
): TimePickerController {
    val activity = LocalContext.current as AppCompatActivity
    val picker = MaterialTimePicker.Builder()
        .setPositiveButtonText("Select")
        .setNegativeButtonText("Cancel")
        .setHour(currentTime.hour)
        .setMinute(currentTime.minute)
        .build()
        .apply {
            addOnDismissListener {
                onDismiss()
            }
            addOnCancelListener {
                onCancel()
            }
            addOnPositiveButtonClickListener {
                val dateTime = LocalTime(hour, minute)
                onTimeSelected(dateTime)
            }
        }

    return TimePickerController(activity, picker)
}

@Composable
actual fun rememberDurationPicker(
    currentDuration: Duration,
    onDismiss: () -> Unit,
    onCancel: () -> Unit,
    onDurationSelected: (Duration) -> Unit
): TimePickerController {
    val activity = LocalContext.current as AppCompatActivity
    val currentHour = currentDuration.inWholeMinutes / 60
    val currentMinute = currentDuration.inWholeMinutes % 60
    val picker = MaterialTimePicker.Builder()
        .setPositiveButtonText("Select")
        .setNegativeButtonText("Cancel")
        .setHour(currentHour.toInt())
        .setMinute(currentMinute.toInt())
        .build()
        .apply {
            addOnDismissListener {
                onDismiss()
            }
            addOnCancelListener {
                onCancel()
            }
            addOnPositiveButtonClickListener {
                val seconds = (hour * 3600) + (minute * 60)
                onDurationSelected(seconds.toDuration(DurationUnit.SECONDS))
            }
        }

    return TimePickerController(activity, picker)
}