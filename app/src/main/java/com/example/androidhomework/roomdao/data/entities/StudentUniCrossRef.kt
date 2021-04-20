package com.example.androidhomework.roomdao.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.androidhomework.roomdao.data.bd.contracts.StudentsContract
import com.example.androidhomework.roomdao.data.bd.contracts.UniContract

@Entity(
    primaryKeys = [StudentsContract.Columns.STUDENT_ID, UniContract.Columns.UNI_ID]
)
data class StudentUniCrossRef (
    @ColumnInfo(name = StudentsContract.Columns.STUDENT_ID )
    val studentId:Long,
    @ColumnInfo(name = UniContract.Columns.UNI_ID)
    val uniId: Long
)

