package com.chipmango.kmp.platform

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class Year(
    val year: Int
) {
    companion object {
        fun now(): Year {
            val currentDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            return Year(currentDateTime.date.year)
        }

        fun from(date: LocalDate): Year = Year(date.year)
    }

    fun isLeap(): Boolean {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)
    }
}