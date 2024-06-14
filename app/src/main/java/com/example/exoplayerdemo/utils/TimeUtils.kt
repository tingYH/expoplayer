package com.example.exoplayerdemo.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object TimeUtils {

    private const val TIME_FORMAT_HH = "HH"

    fun getFormatTime(time: Long): String {
        val dateFormat = SimpleDateFormat(TIME_FORMAT_HH, Locale.getDefault())
        return dateFormat.format(Date(time))
    }

}