package com.chipmango.kmp.platform

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

expect object DateTimeFormatter {
    fun LocalDate.kmpFormat(pattern: String): String
    fun LocalDateTime.kmpFormat(pattern: String): String
    fun LocalTime.kmpFormat(pattern: String): String
}