package com.chipmango.kmp.platform

expect object StringFormatter {
    fun String.kmpFormat(vararg args: Any?): String
}