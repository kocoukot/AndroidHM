package com.example.androidhomework.roomdao.data.bd

import android.content.Context
import androidx.room.Room

object Database {

    lateinit var instance: LearningDataBase
        private set

    fun init(context: Context) {
        instance = Room.databaseBuilder(
            context,
            LearningDataBase::class.java,
            LearningDataBase.DB_NAME
        ).addMigrations(MIGRATION_1_2)
            .build()
    }

}