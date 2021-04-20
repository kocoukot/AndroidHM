package com.example.androidhomework.roomdao.ui.faculties

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomework.roomdao.data.entities.Faculties
import com.example.androidhomework.roomdao.data.entities.University
import com.example.androidhomework.roomdao.ui.FacultyRepository
import com.example.androidhomework.roomdao.ui.UniversityRepository
import kotlinx.coroutines.launch

class FacultyListViewModel : ViewModel() {
    private val facultyRepository = FacultyRepository()

    private val facultyListLiveData = MutableLiveData<List<Faculties>>()

    val facultyList: LiveData<List<Faculties>>
        get() = facultyListLiveData


    fun getAllFaculties(uniId: Long) {
        viewModelScope.launch {
            try {
                facultyListLiveData.postValue(facultyRepository.getAllFaculties(uniId))
            } catch (t: Throwable) {
                Log.e("RoomDao", "Error getting faculties", t)
            }
        }
    }

    fun deleteFaculty(position: Int){
        viewModelScope.launch {
            try {
                val updatedList = facultyRepository.deleteFaculty(facultyListLiveData.value!!, position)
                facultyListLiveData.postValue(updatedList)
            } catch (t: Throwable) {
                Log.e("RoomDao", "Error deleting faculties", t)
            }
        }
    }
}