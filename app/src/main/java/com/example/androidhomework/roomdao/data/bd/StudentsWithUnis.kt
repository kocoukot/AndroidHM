package com.example.androidhomework.roomdao.data.bd

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.androidhomework.roomdao.data.bd.contracts.StudentsContract
import com.example.androidhomework.roomdao.data.bd.contracts.UniContract
import com.example.androidhomework.roomdao.data.entities.Student
import com.example.androidhomework.roomdao.data.entities.University

data class StudentsWithUnis(
    @Embedded
    val student: Student,
    @Relation(
        parentColumn = StudentsContract.Columns.STUDENT_ID,
        entityColumn = UniContract.Columns.UNI_ID,
        associateBy = Junction(StudentUniCrossRef::class)
    )
    val unis: List<University>
)



