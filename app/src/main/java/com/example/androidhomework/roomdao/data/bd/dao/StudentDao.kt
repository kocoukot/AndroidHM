package com.example.androidhomework.roomdao.data.bd.dao

import androidx.room.*
import com.example.androidhomework.roomdao.data.bd.StudentsWithUnis
import com.example.androidhomework.roomdao.data.bd.contracts.StudentsContract
import com.example.androidhomework.roomdao.data.bd.contracts.UniContract
import com.example.androidhomework.roomdao.data.entities.Student

@Dao
interface StudentDao {

    @Query("SELECT * FROM ${StudentsContract.TABLE_NAME}")
    suspend fun getAllStudents(): List<Student>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addStudent(students: Student): Long

    @Query("SELECT * FROM ${StudentsContract.TABLE_NAME}")
    suspend fun getAllStudentsWithUnis(): List<StudentsWithUnis>



    @Query("SELECT ${StudentsContract.Columns.STUDENT_ID} FROM ${StudentsContract.TABLE_NAME} WHERE ${StudentsContract.Columns.NAME} = :studName AND ${StudentsContract.Columns.PHONE_NUM} = :studPhone")
    suspend fun getStudentID(studName: String, studPhone: Int): Long


}