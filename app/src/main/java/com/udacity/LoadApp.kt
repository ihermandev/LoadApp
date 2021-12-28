package com.udacity

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import timber.log.Timber

class LoadApp : Application() {

    override fun onCreate() {
        super.onCreate()

        setupLogger()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(
                getString(R.string.download_notification_channel_id),
                getString(R.string.download_notification_channel_name)
            )
        }
    }

    private fun setupLogger() {
        Timber.plant(Timber.DebugTree())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String) {
        val notificationChannel = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_HIGH
        )
            .apply {
                setShowBadge(false)
            }

        notificationChannel.apply {
            enableLights(true)
            lightColor = Color.RED
            enableVibration(true)
            description = getString(R.string.download_notification_channel_desc)
        }

        val notificationManager = this.getSystemService(
            NotificationManager::class.java
        )
        notificationManager.createNotificationChannel(notificationChannel)
    }

}
