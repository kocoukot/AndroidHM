package com.example.androidhomework.threading.db

import android.content.Context
import androidx.room.Room

object MovieDatabase {

    lateinit var instance: MovieFlowDataBase
        private set

    fun init(context: Context) {
        instance = Room.databaseBuilder(
            context,
            MovieFlowDataBase::class.java,
            MovieFlowDataBase.DB_MOVIE_NAME
        ).build()
    }

}