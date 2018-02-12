package com.adamg.gimmemovie.util

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {
    private const val DF_SIMPLE_STRING = "yyyy-MM-dd'T'HH:mm:ss"
    val DF_SIMPLE_FORMAT = SimpleDateFormat(DF_SIMPLE_STRING, Locale.US)
}

fun Calendar.getYear(): Int {
    return this.get(Calendar.YEAR)
}

fun parseDate(date: String): Calendar {
    val calendar = Calendar.getInstance()
    calendar.time = DateHelper.DF_SIMPLE_FORMAT.parse(date)
    return calendar
}