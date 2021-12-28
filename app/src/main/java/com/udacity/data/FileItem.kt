package com.udacity.data

import java.net.URL

data class FileItem(
    val fileName: String,
    val fileDescription: String,
    val fileUrl: String = "",
    val fileStatus: String = ""
)
