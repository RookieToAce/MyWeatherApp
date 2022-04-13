package com.example.myweatherapp

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class MyWeatherApplication : Application() {

    companion object {
        const val TOKEN = "令牌值"

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}