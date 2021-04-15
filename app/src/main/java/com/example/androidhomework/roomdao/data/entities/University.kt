package com.example.androidhomework.roomdao.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidhomework.roomdao.data.bd.contracts.MarksContract
import com.example.androidhomework.roomdao.data.bd.contracts.StudentsContract
import com.example.androidhomework.roomdao.data.bd.contracts.UniContract

@Entity(
    tableName = UniContract.TABLE_NAME


)
data class University(
    @PrimaryKey
    @ColumnInfo(name = UniContract.Columns.UNI_ID)
    val uniId: Long,
    @ColumnInfo(name = UniContract.Columns.UNI_NAME)
    val uniName: String,
    @ColumnInfo(name = UniContract.Columns.UNI_ADDRESS)
    val uniAddress: String


)