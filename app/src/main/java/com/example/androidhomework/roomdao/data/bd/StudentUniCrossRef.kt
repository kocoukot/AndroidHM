package com.example.androidhomework.roomdao.data.bd

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.androidhomework.roomdao.data.bd.contracts.StudentsContract
import com.example.androidhomework.roomdao.data.bd.contracts.UniContract

@Entity(
    primaryKeys = [StudentsContract.Columns.ID, UniContract.Columns.UNI_NAME]
)
data class StudentUniCrossRef (
    @ColumnInfo(name = StudentsContract.Columns.ID )
    val studentId:Long,
    @ColumnInfo(name = UniContract.Columns.UNI_NAME)
    val uniName: String
)

