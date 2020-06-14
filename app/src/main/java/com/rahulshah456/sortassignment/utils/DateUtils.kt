package com.rahulshah456.sortassignment.utils

import java.text.DateFormatSymbols
import java.util.*

class DateUtils {

    companion object {

        fun getDateValue(calendar: Calendar): String {
            val day = calendar.get(Calendar.DAY_OF_MONTH).toString() + " "
            val month = getMonthForInt(calendar.get(Calendar.MONTH)) + " "
            val year = calendar.get(Calendar.YEAR)
            return day + month + year
        }

        private fun getMonthForInt(num: Int): String? {
            var month = "wrong"
            val dfs = DateFormatSymbols()
            val months: Array<String> = dfs.months
            if (num in 0..11) {
                month = months[num]
            }
            return month
        }
    }
}