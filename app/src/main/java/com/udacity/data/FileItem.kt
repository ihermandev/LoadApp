package com.udacity.data

data class FileItem(
    val fileName: String,
    val fileDescription: String,
    val fileUrl: String = "",
    val fileStatus: String = ""
)
