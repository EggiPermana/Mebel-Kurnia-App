package com.example.mebelkurnia

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}