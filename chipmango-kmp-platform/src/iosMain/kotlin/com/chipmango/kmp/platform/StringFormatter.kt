package com.chipmango.kmp.platform

actual object StringFormatter {
    actual fun String.kmpFormat(vararg args: Any?): String {
        throw UnsupportedOperationException("Not supported on iOS")
    }
}