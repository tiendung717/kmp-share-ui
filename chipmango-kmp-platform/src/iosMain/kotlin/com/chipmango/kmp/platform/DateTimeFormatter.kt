package com.chipmango.kmp.platform

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

actual object DateTimeFormatter {
    actual fun LocalDate.kmpFormat(pattern: String): String {
        throw UnsupportedOperationException("Not supported on iOS")
    }

    actual fun LocalDateTime.kmpFormat(pattern: String): String {
        throw UnsupportedOperationException("Not supported on iOS")
    }

    actual fun LocalTime.kmpFormat(pattern: String): String {
        throw UnsupportedOperationException("Not supported on iOS")
    }


}