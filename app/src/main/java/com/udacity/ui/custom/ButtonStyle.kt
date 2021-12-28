package com.udacity.ui.custom

import android.graphics.Color

//Default values for custom Loading Button
data class ItemStyle(
    var btnTextColor: Int = Color.WHITE,
    var loadingColor: Int = Color.BLUE,
    var btnText: String = "Download",
    var btnCircleColor: Int = Color.YELLOW,
    var btnLoadingDuration: Long = 6500
)

