package com.udacity

import android.app.Application
import timber.log.Timber

class LoadApp : Application() {

    override fun onCreate() {
        super.onCreate()

        setupLogger()
    }

    private fun setupLogger() {
        Timber.plant(Timber.DebugTree())
    }
}
