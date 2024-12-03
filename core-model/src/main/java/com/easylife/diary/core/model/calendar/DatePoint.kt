package com.easylife.diary.core.model.calendar

import java.time.LocalDate


data class DatePoint(
    val date: LocalDate,
    var hasEntry: Boolean = false,
    val isCurrentDate: Boolean = false,
    val active: Boolean = true
)
