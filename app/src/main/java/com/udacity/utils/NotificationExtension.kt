package com.udacity.utils

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.udacity.Const.FILE_DESC_EXTRA
import com.udacity.Const.FILE_NAME_EXTRA
import com.udacity.Const.FILE_STATUS_EXTRA
import com.udacity.Const.NOTIFICATION_ID
import com.udacity.R
import com.udacity.data.FileItem
import com.udacity.ui.DetailActivity
import com.udacity.ui.MainActivity

fun NotificationManager.sendNotification(
    messageBody: String,
    context: Context,
    fileDetail: FileItem? = null,
) {
    val contentIntent = Intent(context, MainActivity::class.java)
    val contentPendingIntent = PendingIntent.getActivity(
        context,
        NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    val detailIntent = Intent(context, DetailActivity::class.java).apply {
        putExtra(FILE_NAME_EXTRA, fileDetail?.fileName)
        putExtra(FILE_DESC_EXTRA, fileDetail?.fileDescription)
        putExtra(FILE_STATUS_EXTRA, fileDetail?.fileStatus)
    }

    val detailPendingIntent: PendingIntent = PendingIntent.getActivity(
        context,
        NOTIFICATION_ID,
        detailIntent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    val builder = NotificationCompat.Builder(
        context,
        context.getString(R.string.download_notification_channel_id)
    )
        .setSmallIcon(R.drawable.ic_downloader)
        .setContentTitle(context
            .getString(R.string.notification_title))
        .setContentText(messageBody)
        .setContentIntent(contentPendingIntent)
        .setAutoCancel(true)
        .setPriority(NotificationCompat.PRIORITY_HIGH)

    if (fileDetail != null) {
        builder.addAction(
            R.drawable.ic_downloader,
            context.getString(R.string.check_download_status),
            detailPendingIntent
        )
    }

    notify(NOTIFICATION_ID, builder.build())
}

fun NotificationManager.cancelNotifications() {
    cancelAll()
}
