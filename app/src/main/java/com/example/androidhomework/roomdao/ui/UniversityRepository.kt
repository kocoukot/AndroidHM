package com.example.androidhomework.roomdao.ui

import android.util.Log
import com.example.androidhomework.roomdao.data.bd.Database
import com.example.androidhomework.roomdao.data.entities.Student
import com.example.androidhomework.roomdao.data.entities.University
import java.text.FieldPosition

class UniversityRepository {

    private val uniDao = Database.instance.uniDao()

    suspend fun getAllUnis(): List<University> {
        return uniDao.getAllUnis()
    }

    suspend fun addUni(uni: University){
        uniDao.addUni(listOf(uni))
    }

    suspend fun deleteUni(uniList: List<University>,position: Int):List<University>{
        uniDao.deleteUni(uniList[position])
        return uniList.filterIndexed { index, _ -> index != position }
    }

    suspend fun getAllUniWithRelations(){
        Log.d("RoomDao",   uniDao.getAllUnisWithFaculties().toString())
    }

    suspend fun getAllUnisWithStudents(){
        Log.d("RoomDao",   uniDao.getAllUnisWithStudents().toString())
    }
}