package com.easylife.diary.core.common.util

import android.text.format.DateUtils
import com.easylife.diary.core.model.DiaryDate
import com.easylife.diary.core.model.DiaryNote
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.time.temporal.WeekFields
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * Created by erenalpaslan on 8.01.2023
 */
object DateUtil {

    private const val DEFAULT_PATTERN = "d MMM yyyy, h:mm a"

    fun getCurrentDate(pattern: String? = DEFAULT_PATTERN): String {
        val current = Calendar.getInstance().time
        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        return formatter.format(current)
    }

    fun DiaryDate.formattedDate(): String? {
        val calendar = Calendar.getInstance()
        calendar.time = Date(this.timestamp)
        val formatter = SimpleDateFormat(DEFAULT_PATTERN, Locale.getDefault())
        return formatter.format(calendar.time)
    }

    fun getCurrentDiaryDate(): DiaryDate? {
        val calendar = Calendar.getInstance()
        val hours = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(calendar.time)
        return DiaryDate(
            hours = hours,
            dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH).toString(),
            shortMonth = calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault()),
            longMonth = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()),
            year = calendar.get(Calendar.YEAR),
            timestamp = calendar.timeInMillis
        )
    }

    fun getCurrentDayOfMonth(): String {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString()
    }

    fun getDayNameList(): List<String?> {
        val weekFields = WeekFields.of(Locale.getDefault())
        var dayOfWeek = weekFields.firstDayOfWeek
        return (0..6).map {
            dayOfWeek = dayOfWeek.plus(1)
            dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
        }
    }
}