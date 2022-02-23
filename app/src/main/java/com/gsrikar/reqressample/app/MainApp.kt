package com.gsrikar.reqressample.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Entry point to the application
 */
@HiltAndroidApp
class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}