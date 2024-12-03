package com.easylife.diary.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by erenalpaslan on 7.01.2023
 */
@Parcelize
data class DiaryDate(
    val hours: String?, //08:32 PM
    val dayOfMonth: String?, //7
    val shortMonth: String?, //Jan
    val longMonth: String?,
    val year: Int?, //2023
    val timestamp: Long, //15600000000
): Parcelable
