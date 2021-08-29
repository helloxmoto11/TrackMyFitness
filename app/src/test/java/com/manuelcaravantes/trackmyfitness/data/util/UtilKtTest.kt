package com.manuelcaravantes.trackmyfitness.data.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.time.LocalDate
import java.time.Month

class UtilKtTest {

    @Test
    fun getDayOfMonthSuffix() {
        val first = "1st"
        val second = "2nd"
        val third = "3rd"
        val fourth = "4th"
        val fifteenth = "15th"
        val twentySecond = "22nd"
        val twelfth = "12th"
        val thirtyFirst = "31st"
        val eighteenth = "18th"

        val result = getDayOfMonthWithSuffix(1)
        val result2 = getDayOfMonthWithSuffix(2)
        val result3 = getDayOfMonthWithSuffix(3)
        val result4 = getDayOfMonthWithSuffix(4)
        val result8 = getDayOfMonthWithSuffix(12)
        val result5 = getDayOfMonthWithSuffix(15)
        val result6 = getDayOfMonthWithSuffix(22)
        val result7 = getDayOfMonthWithSuffix(31)
        val result9 = getDayOfMonthWithSuffix(18)


        assertThat(result).matches(first)
        assertThat(result2).matches(second)
        assertThat(result3).matches(third)
        assertThat(result4).matches(fourth)
        assertThat(result8).matches(twelfth)
        assertThat(result5).matches(fifteenth)
        assertThat(result6).matches(twentySecond)
        assertThat(result7).matches(thirtyFirst)
        assertThat(result9).matches(eighteenth)


    }

    @Test
    fun checkDateFormatter() {
        val today = LocalDate.now()
        val tomorrow = today.plusDays(1)
        val yesterday = today.minusDays(1)

        val date = LocalDate.of(2021, Month.AUGUST, 4)
        val result = "Wednesday, Aug 4th"


        assertThat(formatDate(tomorrow)).matches(TOMORROW)
        assertThat(formatDate(yesterday)).matches(YESTERDAY)
        assertThat(formatDate(today)).matches(TODAY)
        assertThat(formatDate(date)).matches(result)
    }
}