package com.goorm.kkiri

import android.app.Application
import com.goorm.kkiri.util.TokenManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class KkiriApplication : Application() {

    @Inject
    lateinit var tokenManager: TokenManager

    companion object {
        lateinit var application: KkiriApplication

        fun getInstance(): KkiriApplication {
            return application
        }
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}