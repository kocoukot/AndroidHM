package com.example.androidhomework.threading.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidhomework.threading.Movie

@Database(
    entities = [
    Movie::class
    ], version = MovieFlowDataBase.DB_MOVIE_VERSION
)
abstract class MovieFlowDataBase: RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        const val DB_MOVIE_VERSION = 1
        const val DB_MOVIE_NAME = "movie-database"
    }


}