package com.example.fitguide

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FitGuideApp : Application() {

    override fun onCreate() {
        super.onCreate()
        // Perform any initialization tasks here
        Log.d("FitGuideApp", "App started")
    }
}