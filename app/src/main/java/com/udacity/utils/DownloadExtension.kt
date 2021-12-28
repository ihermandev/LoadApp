package com.udacity.utils

import android.app.DownloadManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import com.udacity.Const
import com.udacity.data.FileItem

fun AppCompatActivity.download(
    downloadManager: DownloadManager,
    fileItem: FileItem = Const.defaultFiles[0],
): Long {
    val request = DownloadManager.Request(Uri.parse(fileItem.fileUrl))
        .setTitle(fileItem.fileName)
        .setDescription(fileItem.fileDescription)
        .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        .setRequiresCharging(false)
        .setAllowedOverMetered(true)
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION)

        .setAllowedOverRoaming(true)

    return downloadManager.enqueue(request)// enqueue puts the download request in the queue.
}
