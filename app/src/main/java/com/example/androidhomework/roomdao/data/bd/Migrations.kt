package com.example.androidhomework.roomdao.data.bd

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


val MIGRATION_1_2 = object : Migration(1,2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE students ADD COLUMN gender TEXT NOT NULL DEFAULT 'male' ")
    }
}
