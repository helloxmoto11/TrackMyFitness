package com.manuelcaravantes.trackmyfitness.data.util

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.time.DayOfWeek
import java.time.LocalDate

/**
 * Helper Class For DatePickerComposable
 */
class Calendar {

companion object {
    private const val TAG = "Calendar"
}

    private var _date: LocalDate = LocalDate.now()
    val date get() = _date

    val dayAsString: String get() {
        val dayOfWeek = _date.dayOfWeek.toString().substring(0,3).lowercase().replaceFirstChar { it.uppercase() }
        val month = _date.month.toString().lowercase().replaceFirstChar { it.uppercase() }
        val dayOfMonth = _date.dayOfMonth
        return "$dayOfWeek, $month $dayOfMonth"
    }

    private val dayOfWeek: String = _date.dayOfWeek.toString().lowercase().replaceFirstChar { it.uppercase() }

    private val dayOfMonth = _date.dayOfMonth

    private val month get() = _date.month.toString().lowercase().replaceFirstChar { it.uppercase() }

    val daysInMonth = _date.lengthOfMonth()

    private val year get() = _date.year

    private val isLeapYear = _date.isLeapYear

    var _monthYear = "$month $year"


    fun onIncrementMonth(): String {
        _date = _date.plusMonths(1)
        _monthYear = "$month $year"
        return _monthYear
    }

    fun onDecrementMonth(): String {
        _date = _date.minusMonths(1)
        _monthYear =  "$month $year"
       return _monthYear
    }



}

sealed class WeekDay {
    class Sunday(
        val dayOfWeek: Int = 0,
        val nickName: String = "Sun"
    ): WeekDay()
    class Monday(
        val dayOfWeek: Int = 1,
        val nickName: String = "Mon"
    ): WeekDay()
    class Tuesday(
        val dayOfWeek: Int = 2,
        val nickName: String = "Tue"
    ): WeekDay()
    class Wednesday(
        val dayOfWeek: Int = 3,
        val nickName: String = "Wed"
    ): WeekDay()
    class Thursday(
        val dayOfWeek: Int = 4,
        val nickName: String = "Thu"
    ): WeekDay()
    class Friday(
        val dayOfWeek: Int = 5,
        val nickName: String = "Fri"
    ): WeekDay()
    class Saturday(
        val dayOfWeek: Int = 6,
        val nickName: String = "Sat"
    ): WeekDay()
}