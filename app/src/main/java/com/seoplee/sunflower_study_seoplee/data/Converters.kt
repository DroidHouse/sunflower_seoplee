package com.seoplee.sunflower_study_seoplee.data

import androidx.room.TypeConverter
import java.util.*


/**
 * Room DB에 다양한 데이터 타입을 저장할 수 있게 변환해주는 Converter
 */

class Converters {
    @TypeConverter
    fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter
    fun datestampToCalendar(value: Long): Calendar =
        Calendar.getInstance().apply { timeInMillis = value }
}
