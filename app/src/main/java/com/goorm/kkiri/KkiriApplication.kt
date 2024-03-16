package com.goorm.kkiri

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KkiriApplication : Application() {

    companion object {
        private lateinit var application: KkiriApplication
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}