package com.chipmango.kmp.platform

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toJavaLocalTime
import java.time.format.DateTimeFormatter

actual object DateTimeFormatter {
    actual fun LocalDate.kmpFormat(pattern: String): String {
        return toJavaLocalDate().format(DateTimeFormatter.ofPattern(pattern))
    }
    actual fun LocalDateTime.kmpFormat(pattern: String): String {
        return toJavaLocalDateTime().format(DateTimeFormatter.ofPattern(pattern))
    }

    actual fun LocalTime.kmpFormat(pattern: String): String {
        return toJavaLocalTime().format(DateTimeFormatter.ofPattern(pattern))
    }
}