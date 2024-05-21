package com.chipmango.kmp.platform

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.number
import kotlinx.datetime.toLocalDateTime

data class YearMonth(
    val year: Year,
    val month: Month
) {
    fun plusMonths(value: Long): YearMonth {
        val newMonth = month.number + value
        val newYear = year.year + (newMonth - 1) / 12
        val finalMonth = (newMonth - 1) % 12 + 1
        return YearMonth(Year(newYear.toInt()), Month(finalMonth.toInt()))
    }

    fun minusMonths(value: Long): YearMonth = plusMonths(-value)
    fun isBefore(yearMonth: YearMonth): Boolean {
        return year.year < yearMonth.year.year || (year == yearMonth.year && month < yearMonth.month)
    }

    companion object {

        fun now(): YearMonth {
            val currentDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            return YearMonth(Year(currentDateTime.year), currentDateTime.date.month)
        }

        fun from(date: LocalDate): YearMonth = YearMonth(Year.from(date), date.month)
    }
}