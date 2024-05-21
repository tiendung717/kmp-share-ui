package com.chipmango.kmp.platform

actual object StringFormatter {
    actual fun String.kmpFormat(vararg args: Any?): String {
        return String.Companion.format(this, *args)
    }
}