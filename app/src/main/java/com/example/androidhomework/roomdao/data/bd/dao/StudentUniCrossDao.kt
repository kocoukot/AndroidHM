package com.example.androidhomework.roomdao.data.bd.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.androidhomework.roomdao.data.entities.StudentUniCrossRef

@Dao
interface StudentUniCrossDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putStudentUniCross(studentUniCross : List<StudentUniCrossRef>)

}