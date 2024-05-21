package com.chipmango.kmp.uikit.calendar.extension

import com.chipmango.kmp.platform.KmpDateTime.daysIn
import com.chipmango.kmp.platform.KmpDateTime.previousOrSame
import com.chipmango.kmp.platform.Year
import com.chipmango.kmp.platform.YearMonth
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.plus


fun YearMonth.weeks(firstDayOfWeek: DayOfWeek, includeDays: Boolean): List<List<LocalDate?>> {
    val firstDayOfMonth = getFirstDayOfMonth(firstDayOfWeek)
    val lastDayOfMonth = getLastDayOfMonth(firstDayOfWeek)

    val weeks = mutableListOf<List<LocalDate?>>()
    var currentDay = firstDayOfMonth
    while (currentDay <= lastDayOfMonth) {
        val week = (0 until 7).map { dayOffset ->
            val day = currentDay.plus(dayOffset.toLong(), DateTimeUnit.DAY)
            when (day.month) {
                month -> day
                else -> when (includeDays) {
                    true -> day
                    false -> null
                }
            }
        }
        weeks.add(week)
        currentDay = currentDay.plus(7, DateTimeUnit.DAY)
    }
    return weeks
}

fun YearMonth.getFirstDayOfMonth(firstDayOfWeek: DayOfWeek = DayOfWeek.SUNDAY): LocalDate {
    val firstDay = LocalDate(year.year, month, 1)
    return firstDay.previousOrSame(firstDayOfWeek)
}

fun YearMonth.getLastDayOfMonth(firstDayOfWeek: DayOfWeek = DayOfWeek.SUNDAY): LocalDate {
    val lastDayOfWeek = when (firstDayOfWeek) {
        DayOfWeek.SUNDAY -> DayOfWeek.SATURDAY
        else -> DayOfWeek.SUNDAY
    }
    val lengthInDays = month.daysIn(year)
    val lastDay = LocalDate(year.year, month, lengthInDays)
    return lastDay.previousOrSame(lastDayOfWeek)
}

fun getMonthsInYear(year: Year): List<YearMonth> {
    return (1..12).map { month ->
        YearMonth(year, Month(month))
    }
}