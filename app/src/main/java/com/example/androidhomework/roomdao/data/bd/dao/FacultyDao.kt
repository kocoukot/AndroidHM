package com.example.androidhomework.roomdao.data.bd.dao

import androidx.room.*
import com.example.androidhomework.roomdao.data.bd.contracts.FacultiesContract
import com.example.androidhomework.roomdao.data.bd.contracts.StudentsContract
import com.example.androidhomework.roomdao.data.bd.contracts.UniContract
import com.example.androidhomework.roomdao.data.entities.Faculties
import com.example.androidhomework.roomdao.data.entities.Student
import com.example.androidhomework.roomdao.data.entities.University

@Dao
interface FacultyDao {

    @Query("SELECT * FROM ${FacultiesContract.TABLE_NAME} WHERE ${FacultiesContract.Columns.FACULTY_UNI_ID} = :uniId")
    suspend fun getAllFaculties(uniId: Long): List<Faculties>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFaculty(faculties : List<Faculties>)

    @Delete
    suspend fun deleteFaculty(faculty: Faculties)


}