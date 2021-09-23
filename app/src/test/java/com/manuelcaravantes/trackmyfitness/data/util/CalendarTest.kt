package com.manuelcaravantes.trackmyfitness.data.util

import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import java.time.LocalDate


class CalendarTest {

    lateinit var calendar: Calendar

    @Before
    fun init() {
        calendar = Calendar()
    }

    @Test
    fun getDate() {
        val date = LocalDate.now()
        assertEquals(date, calendar.date)
    }

    @Test
    fun `get the day as a string for calendar header`() {
        //change this to current day when test is run
        val today = "Wed, September 22"

        assertEquals(today, calendar.dayAsString)
    }

    @Test
    fun getDaysInMonth() {
        //val calendar = Calendar()
        val daysInSeptember = 30
        assertEquals(daysInSeptember, calendar.daysInMonth)
    }

    @Test
    fun onIncrementMonth() {
        val today = LocalDate.now()
        val nextMonth = today.plusMonths(1)

        calendar.onIncrementMonth()
        assertEquals(nextMonth, calendar.date)
    }

    @Test
    fun onDecrementMonth() {
        val today = LocalDate.now()
        val lastMonth = today.minusMonths(1)

        calendar.onDecrementMonth()
        assertEquals(lastMonth, calendar.date)
    }
}