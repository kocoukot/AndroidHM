package com.example.androidhomework.roomdao.data.bd

import androidx.room.TypeConverter

class YearsToLearnConverter {

    @TypeConverter
    fun convertYearToString(years:YearsToLearn): String = years.name

    @TypeConverter
    fun convertStringToYear(yearString: String): YearsToLearn = YearsToLearn.valueOf(yearString)
}