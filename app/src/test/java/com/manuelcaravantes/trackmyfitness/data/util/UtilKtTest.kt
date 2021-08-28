package com.manuelcaravantes.trackmyfitness.data.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.time.LocalDate

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
        val dateIn = LocalDate.of(2021, 8, 23)
        val dateOut = formatDate(dateIn)

        val result = "Monday, Aug 23rd"

        assertThat(dateOut).matches(result)
    }
}