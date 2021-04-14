package com.example.androidhomework.roomdao.data.bd

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.androidhomework.roomdao.data.bd.contracts.StudentsContract
import com.example.androidhomework.roomdao.data.bd.contracts.UniContract
import com.example.androidhomework.roomdao.data.entities.Student
import com.example.androidhomework.roomdao.data.entities.University

data class UnisWithStudents(
    @Embedded
    val uni: University,
    @Relation(
        parentColumn = UniContract.Columns.UNI_NAME,
        entityColumn = StudentsContract.Columns.ID,
        associateBy = Junction(StudentUniCrossRef::class)
    )
    val students: List<Student>

)