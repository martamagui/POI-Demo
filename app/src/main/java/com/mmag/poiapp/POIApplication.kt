package com.mmag.poiapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class POIApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}