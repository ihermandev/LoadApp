package com.udacity.utils

import android.app.Activity
import android.os.Bundle
import android.widget.Toast

fun Activity.showShortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

