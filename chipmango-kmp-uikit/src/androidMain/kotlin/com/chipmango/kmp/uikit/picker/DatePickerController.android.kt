package com.chipmango.kmp.uikit.picker

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ComponentActivity
import com.google.android.material.datepicker.MaterialDatePicker

actual data class DatePickerController(private val activity: AppCompatActivity, private val materialDatePicker: MaterialDatePicker<Long>) {

    actual fun show() {
        materialDatePicker.show(activity.supportFragmentManager, materialDatePicker.toString())
    }
}