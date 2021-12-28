package com.udacity

import com.udacity.data.FileItem

object Const {
    const val GLIDE_URL =
        "https://github.com/bumptech/glide/archive/master.zip"
    const val LOAD_APP_URL =
        "https://github.com/udacity/nd940-c3-advanced-android-programming-project-starter/archive/master.zi"
    const val RETROFIT_URL =
        "https://github.com/square/retrofit/archive/master.zip"

    var defaultFiles = mutableListOf<FileItem>(
        FileItem(fileName = "Glide",
            fileDescription = "Image Loading Library by BumpTech",
            fileUrl = GLIDE_URL),
        FileItem(fileName = "LoadApp",
            fileDescription = "Current repository by Udacity",
            fileUrl = LOAD_APP_URL),
        FileItem(fileName = "Retrofit",
            fileDescription = "A type-safe HTTP client for Android by Square",
            fileUrl = RETROFIT_URL)
    )

    const val NOTIFICATION_ID = 0

    const val FILE_NAME_EXTRA = "FILE_NAME"
    const val FILE_DESC_EXTRA = "FILE_DESCRIPTION"
    const val FILE_STATUS_EXTRA = "FILE_STATUS"
}
