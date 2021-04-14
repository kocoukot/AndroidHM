package com.example.androidhomework.roomdao.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.androidhomework.roomdao.data.bd.contracts.MarksContract
import com.example.androidhomework.roomdao.data.bd.contracts.StudentsContract

@Entity(
    tableName = MarksContract.TABLE_NAME,

    foreignKeys = [ForeignKey(
        entity = Student::class,
        parentColumns = [StudentsContract.Columns.ID],
        childColumns = [MarksContract.Columns.MARK_ID]
    )]
)
data class Marks(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = MarksContract.Columns.MARK_ID)
    val markId: Long,
    @ColumnInfo(name = MarksContract.Columns.CLASS_ID)
    val classId: Long,
    @ColumnInfo(name = MarksContract.Columns.STUDENT_ID)
    val studentId: Long,
    @ColumnInfo(name = MarksContract.Columns.MARK)
    val mark: Int


)