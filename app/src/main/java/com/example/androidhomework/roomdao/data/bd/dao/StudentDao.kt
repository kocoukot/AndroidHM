package com.example.androidhomework.roomdao.data.bd.dao

import androidx.room.*
import com.example.androidhomework.roomdao.data.bd.StudentsWithUnis
import com.example.androidhomework.roomdao.data.bd.contracts.StudentsContract
import com.example.androidhomework.roomdao.data.entities.Student

@Dao
interface StudentDao {

    @Query("SELECT * FROM ${StudentsContract.TABLE_NAME}")
    suspend fun getAllStudents(): List<Student>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun addStudent(students: List<Student>)

    @Transaction
    @Query("SELECT * FROM ${StudentsContract.TABLE_NAME}")
    suspend fun getAllStudentsWithUnis(): List<StudentsWithUnis>

    @Query("SELECT * FROM ${StudentsContract.TABLE_NAME} WHERE ${StudentsContract.Columns.ID} = :id")
    suspend fun getStudentsByID(id: Long): List<Student>

}