package com.ebt.common.utils.date

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    fun formatDate(timestamp: Long, targetFormat: String = "dd-MM-yyyy HH:mm"): String {
        return SimpleDateFormat(targetFormat, Locale.getDefault()).format(timestamp)
    }
}