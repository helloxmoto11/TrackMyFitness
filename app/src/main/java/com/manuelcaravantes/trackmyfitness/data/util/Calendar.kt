package com.manuelcaravantes.trackmyfitness.data.util

import java.time.DayOfWeek
import java.time.LocalDate

/**
 * Helper Class For DatePickerComposable
 */
class Calendar {

    private var _date: LocalDate = LocalDate.now()
    val date get() = _date

    val dayAsString: String get() {
        val dayOfWeek = date.dayOfWeek.toString().substring(0,3)
        val month = date.month.toString().lowercase().replaceFirstChar { it.uppercase() }
        val dayOfMonth = date.dayOfMonth
        return "$dayOfWeek, $month $dayOfMonth"
    }

    private val dayOfWeek: DayOfWeek = _date.dayOfWeek

    private val dayOfMonth = _date.dayOfMonth

    private val month = _date.month

    private val year = _date.year

    private val isLeapYear = _date.isLeapYear

    fun onIncrementDay() {
        _date = _date.plusDays(1)
    }

    fun onDecrementDay() {
        _date = _date.minusDays(1)
    }



}