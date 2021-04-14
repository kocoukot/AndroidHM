package com.example.androidhomework.roomdao.data.bd

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.androidhomework.roomdao.data.bd.contracts.FacultiesContract
import com.example.androidhomework.roomdao.data.bd.contracts.UniContract
import com.example.androidhomework.roomdao.data.entities.Faculties
import com.example.androidhomework.roomdao.data.entities.University

data class UniWithFaculties(
    @Embedded
    val uni: University,
    @Relation(
        parentColumn = UniContract.Columns.UNI_NAME,
        entityColumn =  FacultiesContract.Columns.FACULTY_UNI_NAME
    )
    val faculty: List<Faculties>

)