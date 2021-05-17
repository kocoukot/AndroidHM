package com.example.androidhomework.threading.db

import androidx.room.TypeConverter
import com.example.androidhomework.roomdao.data.bd.YearsToLearn
import com.example.androidhomework.threading.MovieType

class MovieTypeConverter {
    @TypeConverter
    fun convertTypeToString(type: MovieType): String = type.name

    @TypeConverter
    fun convertStringToType(typeString: String): MovieType = MovieType.valueOf(typeString)
}