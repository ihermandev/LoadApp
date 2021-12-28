package com.udacity.ui

import android.app.NotificationManager
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.udacity.Const.FILE_DESC_EXTRA
import com.udacity.Const.FILE_NAME_EXTRA
import com.udacity.Const.FILE_STATUS_EXTRA
import com.udacity.Const.NOTIFICATION_ID
import com.udacity.R

class DetailActivity : AppCompatActivity() {

    private val toolbar by lazy {
        findViewById<Toolbar>(R.id.toolbar)
    }

    private val itemName by lazy {
        findViewById<AppCompatTextView>(R.id.tvFileName)
    }
    private val itemDesc by lazy {
        findViewById<AppCompatTextView>(R.id.tvFileNameDesc)
    }

    private val itemStatus by lazy {
        findViewById<AppCompatTextView>(R.id.tvStatusDesc)
    }

    private val btnOk by lazy {
        findViewById<AppCompatButton>(R.id.btnOK)
    }

    private val notificationManager by lazy {
        ContextCompat.getSystemService(
            applicationContext,
            NotificationManager::class.java
        ) as NotificationManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        itemName.text = intent.getStringExtra(FILE_NAME_EXTRA)
        itemDesc.text = intent.getStringExtra(FILE_DESC_EXTRA)

        intent.getStringExtra(FILE_STATUS_EXTRA)?.let { status ->
            when (status) {
                "Success" -> {
                    itemStatus.text = status
                    itemStatus.setTextColor(Color.GREEN)
                }
                else -> {
                    itemStatus.text = status
                    itemStatus.setTextColor(Color.RED)
                }
            }
        }

        btnOk.setOnClickListener {
            notificationManager.cancel(NOTIFICATION_ID)
            startActivity(Intent(this, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            })
        }
    }
}
