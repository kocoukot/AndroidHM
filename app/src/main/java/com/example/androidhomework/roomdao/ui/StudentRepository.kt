package com.example.androidhomework.roomdao.ui

import android.util.Log
import com.example.androidhomework.roomdao.data.entities.Student
import com.example.androidhomework.roomdao.data.bd.Database
import com.example.androidhomework.roomdao.data.entities.StudentUniCrossRef
import com.example.androidhomework.roomdao.data.entities.University

class StudentRepository {

    private val studentDao = Database.instance.studentDao()
    private val uniDao = Database.instance.uniDao()
    private val studentUniDao = Database.instance.studentUniCross()

    suspend fun getAllStudents(): List<Student> {
        return studentDao.getAllStudents()
    }

    suspend fun addStudent(
        name: String,
        age: Int,
        phone: Int,
        selectedUni: Int
    ) {
        val uniId = uniDao.getAllUnis()[selectedUni].uniId
        val student =
            Student(
                id = 0,
                name = name,
                age = age,
                gender = "male",
                phoneNumber = phone,
                uniId = uniId
            )

        studentDao.addStudent(student)
        val studentId = studentDao.getStudentID(name, phone)
        val studentUniCross = StudentUniCrossRef(studentId, uniId)
        studentUniDao.putStudentUniCross(listOf(studentUniCross))
    }

    suspend fun getAllStudentsWithUnis() {
        Log.d("RoomDao", studentDao.getAllStudentsWithUnis().toString())
    }


}