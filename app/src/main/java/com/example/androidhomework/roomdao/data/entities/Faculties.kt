package com.example.androidhomework.roomdao.data.entities

import androidx.room.*
import com.example.androidhomework.roomdao.data.bd.YearsToLearn
import com.example.androidhomework.roomdao.data.bd.YearsToLearnConverter
import com.example.androidhomework.roomdao.data.bd.contracts.FacultiesContract
import com.example.androidhomework.roomdao.data.bd.contracts.UniContract

@Entity(
    tableName = FacultiesContract.TABLE_NAME,
    indices = [Index(FacultiesContract.Columns.FACULTY_UNI_NAME)],

    foreignKeys = [ForeignKey(
        entity = University::class,
        parentColumns = [
            UniContract.Columns.UNI_ID
        ],
        childColumns = [
            FacultiesContract.Columns.FACULTY_UNI_ID
        ],
        onDelete = ForeignKey.CASCADE
    )
    ]


)
@TypeConverters(YearsToLearnConverter::class)
data class Faculties(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = FacultiesContract.Columns.FACULTY_ID)
    val id: Long,
    @ColumnInfo(name = FacultiesContract.Columns.FACULTY_NAME)
    val facultyName: String,
    @ColumnInfo(name = FacultiesContract.Columns.FACULTY_ADDRESS)
    val facultyAddress: String,
    @ColumnInfo(name = FacultiesContract.Columns.FACULTY_YEARS_TO_LEARN)
    val yearsToLearn: YearsToLearn,
    @ColumnInfo(name = FacultiesContract.Columns.FACULTY_UNI_ID)
    val facultyUniId: Long,
    @ColumnInfo(name = FacultiesContract.Columns.FACULTY_UNI_NAME)
    val facultyUniName: String,
    @ColumnInfo(name = FacultiesContract.Columns.FACULTY_UNI_ADDRESS)
    val facultyUniAddress: String

)