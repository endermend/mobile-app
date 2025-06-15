package com.example.trainingapp.data

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MyConverter {
    private val formatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    @TypeConverter
    fun convertToDatabaseColumn(locDate: LocalDateTime): String {
        return locDate.format(formatter)
    }

    @TypeConverter
    fun convertToEntityAttribute(sqlDate: String): LocalDateTime {
        return LocalDateTime.parse(sqlDate, formatter)
    }
}