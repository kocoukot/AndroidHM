package com.example.androidhomework.roomdao.data.bd

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidhomework.roomdao.data.bd.dao.FacultyDao
import com.example.androidhomework.roomdao.data.bd.dao.StudentDao
import com.example.androidhomework.roomdao.data.bd.dao.UniDao
import com.example.androidhomework.roomdao.data.entities.Faculties
import com.example.androidhomework.roomdao.data.entities.Marks
import com.example.androidhomework.roomdao.data.entities.Student
import com.example.androidhomework.roomdao.data.entities.University

@Database(
    entities = [
        Student::class,
        Marks::class,
        University::class,
        Faculties::class,
    StudentUniCrossRef::class
    ], version = LearningDataBase.DB_VERSION
)
abstract class LearningDataBase : RoomDatabase() {

    abstract fun studentDao(): StudentDao
    abstract fun uniDao(): UniDao
    abstract fun facultyDao(): FacultyDao


    companion object {
        const val DB_VERSION = 2
        const val DB_NAME = "learning-database"

    }
}