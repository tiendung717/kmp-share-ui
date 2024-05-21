package com.chipmango.kmp.uikit.picker

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.timepicker.MaterialTimePicker

actual class TimePickerController(
    private val activity: AppCompatActivity,
    private val picker: MaterialTimePicker
) {
    actual fun show() {
        activity.supportFragmentManager.let {
            picker.show(it, picker.toString())
        }
    }
}