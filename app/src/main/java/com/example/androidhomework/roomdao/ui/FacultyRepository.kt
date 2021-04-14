package com.example.androidhomework.roomdao.ui

import com.example.androidhomework.roomdao.data.bd.Database
import com.example.androidhomework.roomdao.data.entities.Faculties
import com.example.androidhomework.roomdao.data.entities.Student
import com.example.androidhomework.roomdao.data.entities.University
import java.text.FieldPosition

class FacultyRepository {

    private val facultyDao = Database.instance.facultyDao()

    suspend fun getAllFaculties(uniName: String): List<Faculties> {
        return facultyDao.getAllFaculties(uniName)
    }

    suspend fun addFaculty(faculty: Faculties){
        facultyDao.addFaculty(listOf(faculty))
    }

    suspend fun deleteFaculty(facultyList: List<Faculties>,position: Int):List<Faculties>{
        facultyDao.deleteFaculty(facultyList[position])
        return facultyList.filterIndexed { index, _ -> index != position }
    }
}