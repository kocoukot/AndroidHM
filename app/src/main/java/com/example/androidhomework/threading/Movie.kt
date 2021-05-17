package com.example.androidhomework.threading

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.androidhomework.roomdao.data.bd.YearsToLearn
import com.example.androidhomework.roomdao.data.bd.YearsToLearnConverter
import com.example.androidhomework.roomdao.data.bd.contracts.FacultiesContract
import com.example.androidhomework.threading.db.MovieContract
import com.example.androidhomework.threading.db.MovieTypeConverter

@Entity(
    tableName = MovieContract.TABLE_NAME
)
@TypeConverters(MovieTypeConverter::class)
data class Movie(
    @ColumnInfo(name = MovieContract.Columns.MOVIE_TITLE)
    val title: String,
    @PrimaryKey
    @ColumnInfo(name = MovieContract.Columns.MOVIE_ID)
    val movieId: String,
    @ColumnInfo(name = MovieContract.Columns.MOVIE_POSTER)
    val poster: String,
    @ColumnInfo(name = MovieContract.Columns.MOVIE_TYPE)
    val type: MovieType

)
