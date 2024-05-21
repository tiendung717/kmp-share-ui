package com.solid.kmp

import android.app.Application
import shared.di.KoinInitializer

class AndroidApp : Application() {

    override fun onCreate() {
        super.onCreate()
        KoinInitializer(applicationContext).init()
    }
}