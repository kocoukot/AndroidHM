package com.example.androidhomework.roomdao.ui

import android.util.Log
import com.example.androidhomework.roomdao.data.entities.Student
import com.example.androidhomework.roomdao.data.bd.Database
import com.example.androidhomework.roomdao.data.entities.University

class StudentRepository {

    private val studentDao = Database.instance.studentDao()
    private val uniDao = Database.instance.uniDao()

    suspend fun getAllStudents(): List<Student> {
        return studentDao.getAllStudents()
    }

    suspend fun addStudent(student: Student){
        val uni = University(student.uniName!!,student.uniAddress!!)
        uniDao.addUni(listOf(uni))
        studentDao.addStudent(listOf(student))
    }
    suspend fun getAllStudentsWithUnis(){
        Log.d("RoomDao",   studentDao.getAllStudentsWithUnis().toString())
    }

}