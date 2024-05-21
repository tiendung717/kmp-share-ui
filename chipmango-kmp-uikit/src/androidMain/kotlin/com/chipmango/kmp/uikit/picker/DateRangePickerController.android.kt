package com.chipmango.kmp.uikit.picker

import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.Pair
import com.google.android.material.datepicker.MaterialDatePicker

actual class DateRangePickerController(
    private val activity: AppCompatActivity,
    private val materialDatePicker: MaterialDatePicker<Pair<Long, Long>>
) {

    actual fun show() {
        materialDatePicker.show(activity.supportFragmentManager, materialDatePicker.toString())
    }
}