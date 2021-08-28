package com.manuelcaravantes.trackmyfitness.data.util

import java.time.LocalDate

const val TAG = "DebuggingLog"
const val TODAY = "Today"

/**
 * takes day of month and returns value with suffix
 * @param day of month
 * @return formatted day with suffix. ex: 21 -> 21st
 */
fun getDayOfMonthWithSuffix(day: Int): String {
    if (day in 11..13) return """${day}th"""
    return when (day % 10) {
        1 -> """${day}st"""
        2 -> """${day}nd"""
        3 -> """${day}rd"""
        else -> """${day}th"""
    }
}

/**
 * takes LocalDate instance and returns String.
 * @param date to be formatted
 * @return date as string. ex: Monday, Aug. 28th
 *
 */
fun formatDate(date: LocalDate): String {
    val dayOfWeek = date.dayOfWeek.toString().lowercase().replaceFirstChar { it.uppercase() }
    val month = date.month.toString().substring(0,3).lowercase().replaceFirstChar { it.uppercase() }
    val day = getDayOfMonthWithSuffix(date.dayOfMonth)
    return "$dayOfWeek, $month $day"
}