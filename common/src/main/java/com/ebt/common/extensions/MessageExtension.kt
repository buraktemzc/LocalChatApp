package com.ebt.common.extensions

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.toast(@StringRes messageId: Int) {
    Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show()
}