package com.example.androidhomework.roomdao.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidhomework.roomdao.data.bd.contracts.MarksContract
import com.example.androidhomework.roomdao.data.bd.contracts.StudentsContract
import com.example.androidhomework.roomdao.data.bd.contracts.UniContract

@Entity(
    tableName = UniContract.TABLE_NAME,
    primaryKeys = [
        UniContract.Columns.UNI_NAME,
        UniContract.Columns.UNI_ADDRESS
    ]
)
data class University(
    @ColumnInfo(name = UniContract.Columns.UNI_NAME)
    val uniName: String,
    @ColumnInfo(name = UniContract.Columns.UNI_ADDRESS)
    val uniAddress: String


)