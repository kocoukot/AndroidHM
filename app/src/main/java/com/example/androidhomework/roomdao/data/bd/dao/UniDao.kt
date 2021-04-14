package com.example.androidhomework.roomdao.data.bd.dao

import androidx.room.*
import com.example.androidhomework.roomdao.data.bd.UniWithFaculties
import com.example.androidhomework.roomdao.data.bd.UnisWithStudents
import com.example.androidhomework.roomdao.data.bd.contracts.StudentsContract
import com.example.androidhomework.roomdao.data.bd.contracts.UniContract
import com.example.androidhomework.roomdao.data.entities.Student
import com.example.androidhomework.roomdao.data.entities.University

@Dao
interface UniDao {

    @Query("SELECT * FROM ${UniContract.TABLE_NAME}")
    suspend fun getAllUnis(): List<University>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUni(universities : List<University>)

    @Delete
    suspend fun deleteUni(university: University)

    @Query("SELECT * FROM ${UniContract.TABLE_NAME}")
    suspend fun getAllUnisWithFaculties():List<UniWithFaculties>

    @Transaction
    @Query("SELECT * FROM ${UniContract.TABLE_NAME}")
    suspend fun getAllUnisWithStudents(): List<UnisWithStudents>
}