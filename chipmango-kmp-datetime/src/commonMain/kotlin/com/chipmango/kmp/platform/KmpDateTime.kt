package com.chipmango.kmp.platform

import kotlinx.datetime.Clock
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

object KmpDateTime {
    fun now(): LocalDate {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    }

    fun dateTimeNow(): LocalDateTime {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    }

    fun LocalDate.previousOrSame(dayOfWeek: DayOfWeek): LocalDate {
        val dowValue: Int = dayOfWeek.ordinal
        val daysDiff = dowValue - this.dayOfWeek.ordinal
        return if (daysDiff < 0) this.plus((-daysDiff).toLong(), kotlinx.datetime.DateTimeUnit.DAY)
        else this
    }

    fun Month.daysIn(year: Year): Int {
        return when (this) {
            Month.JANUARY, Month.MARCH, Month.MAY, Month.JULY, Month.AUGUST, Month.OCTOBER, Month.DECEMBER -> 31
            Month.APRIL, Month.JUNE, Month.SEPTEMBER, Month.NOVEMBER -> 30
            Month.FEBRUARY -> if (year.isLeap()) 29 else 28
            else -> 30
        }
    }

    fun YearMonth.atDay(day: Int): LocalDate {
        return LocalDate(year = year.year, month = month, dayOfMonth = day)
    }

    fun YearMonth.atEndOfMonth(): LocalDate {
        return LocalDate(year = year.year, month = month, dayOfMonth = month.daysIn(year))
    }

    fun LocalDateTime.isAfter(other: LocalDateTime): Boolean {
        return this > other
    }

    fun LocalDateTime.isBefore(other: LocalDateTime): Boolean {
        return this < other
    }

    fun LocalTime.isAfter(other: LocalTime): Boolean {
        return this > other
    }

    fun LocalTime.isBefore(other: LocalTime): Boolean {
        return this < other
    }

    fun ofHours(hours: Long): Duration {
        return hours.toDuration(DurationUnit.HOURS)
    }

    fun ofMinutes(minutes: Long): Duration {
        return minutes.toDuration(DurationUnit.MINUTES)
    }

    fun ofSeconds(seconds: Long): Duration {
        return seconds.toDuration(DurationUnit.SECONDS)
    }

    fun LocalTime.plus(duration: Duration) : LocalTime {
        return LocalTime.fromMillisecondOfDay((this.toMillisecondOfDay() + duration.inWholeSeconds * 1000).toInt())
    }

    fun LocalTime.minus(duration: Duration) : LocalTime {
        return LocalTime.fromMillisecondOfDay((this.toMillisecondOfDay() - duration.inWholeSeconds * 1000).toInt())
    }

    fun daysBetween(start: LocalDate, end: LocalDate): Long {
        val dates = mutableListOf<LocalDate>()
        var current = start
        while (current <= end) {
            dates.add(current)
            current = current.plus(1, kotlinx.datetime.DateTimeUnit.DAY)
        }
        return dates.size.toLong()
    }

    fun LocalDate.withDayOfMonth(day: Int): LocalDate {
        return LocalDate(year, month, day)
    }

    fun LocalDate.withDayOfYear(day: Int): LocalDate {
        return LocalDate(year, month, day)
    }
}