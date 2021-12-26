package com.udacity

import android.app.DownloadManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NotificationCompat


class MainActivity : AppCompatActivity() {

    private var downloadID: Long = 0

    private lateinit var notificationManager: NotificationManager
    private lateinit var pendingIntent: PendingIntent
    private lateinit var action: NotificationCompat.Action

    private val toolbar by lazy {
        findViewById<Toolbar>(R.id.toolbar)
    }

    private val customButton by lazy {
        findViewById<LoadingButton>(R.id.custom_button)
    }

    private val radioGroup by lazy {
        findViewById<RadioGroup>(R.id.rg_download_options)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        registerReceiver(receiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))

        customButton.setOnClickListener {
            //TODO complete the logic
            //download()
            checkDownloadOption()
        }
    }

    private fun checkDownloadOption() {
        val chosenOption = radioGroup.checkedRadioButtonId

        if (chosenOption == -1) {
            showShortToast(getString(R.string.no_option_chosen))
            return
        }

        when (chosenOption) {
            R.id.rb_glide -> {
                //TODO replace with needed func
                showShortToast("Glide")
            }
            R.id.rb_load_app -> {
                //TODO replace with needed func
                showShortToast("Load App")
            }
            R.id.rb_retrofit -> {
                //TODO replace with needed func
                showShortToast("Retrofit")
            }
        }
    }

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
        }
    }

    private fun download() {
        val request =
            DownloadManager.Request(Uri.parse(URL))
                .setTitle(getString(R.string.app_name))
                .setDescription(getString(R.string.app_description))
                .setRequiresCharging(false)
                .setAllowedOverMetered(true)
                .setAllowedOverRoaming(true)

        val downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        downloadID =
            downloadManager.enqueue(request)// enqueue puts the download request in the queue.
    }

    companion object {
        private const val URL =
            "https://github.com/udacity/nd940-c3-advanced-android-programming-project-starter/archive/master.zip"
        private const val CHANNEL_ID = "channelId"
    }

}
