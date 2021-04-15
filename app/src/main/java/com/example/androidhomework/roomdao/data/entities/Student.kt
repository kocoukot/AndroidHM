package com.example.androidhomework.roomdao.data.entities

import androidx.room.*
import com.example.androidhomework.roomdao.data.bd.contracts.StudentsContract

@Entity(
    tableName = StudentsContract.TABLE_NAME,
    indices = [
    Index(StudentsContract.Columns.STUDENT_ID)
    ]
//    ,
//
//    foreignKeys = [ForeignKey(
//        entity = University::class,
//        parentColumns = [
//            UniContract.Columns.UNI_ID
//        ],
//        childColumns = [
//            StudentsContract.Columns.UNIVERSITY_ID
//        ],
//        onDelete = ForeignKey.CASCADE
//    )
//    ]
)

data class Student(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = StudentsContract.Columns.STUDENT_ID)
    val id: Long,
    @ColumnInfo(name = StudentsContract.Columns.NAME)
    val name: String,
    @ColumnInfo(name = StudentsContract.Columns.AGE)
    val age: Int,
    @ColumnInfo(name = StudentsContract.Columns.PHONE_NUM)
    val phoneNumber: Int,
    @ColumnInfo(name = StudentsContract.Columns.GENDER)
    val gender: String,
    @ColumnInfo(name = StudentsContract.Columns.UNIVERSITY_ID )
    val uniId: Long,
    @ColumnInfo(name = StudentsContract.Columns.UNIVERSITY_NAME )
    val uniName: String?,
    @ColumnInfo(name = StudentsContract.Columns.UNIVERSITY_ADDRESS)
    val uniAddress: String?

)
