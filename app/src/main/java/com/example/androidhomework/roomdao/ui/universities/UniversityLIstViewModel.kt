package com.example.androidhomework.roomdao.ui.universities

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomework.roomdao.data.entities.University
import com.example.androidhomework.roomdao.ui.UniversityRepository
import kotlinx.coroutines.launch

class UniversityLIstViewModel : ViewModel() {
    private val universityRepository = UniversityRepository()

    private val universityListLiveData = MutableLiveData<List<University>>()

    val universityList: LiveData<List<University>>
        get() = universityListLiveData


    fun getAllUnis() {
        viewModelScope.launch {
            try {
                universityListLiveData.postValue(universityRepository.getAllUnis())
            } catch (t: Throwable) {
                Log.e("RoomDao", "Error getting universities", t)
            }
        }
    }

    fun deleteUni(position: Int){
        viewModelScope.launch {
            try {
                val updatedList = universityRepository.deleteUni(universityListLiveData.value!!, position)
                universityListLiveData.postValue(updatedList)
            } catch (t: Throwable) {
                Log.e("RoomDao", "Error deleting universities", t)
            }
        }
    }

    fun getAllUniWithRelations(){
        viewModelScope.launch {
            try {
                universityRepository.getAllUniWithRelations()

            } catch (t: Throwable) {
                Log.e("RoomDao", "Error deleting universities", t)
            }
        }
    }

    fun getAllUnisWithStudents(){
        viewModelScope.launch {
            try {
                universityRepository.getAllUnisWithStudents()

            } catch (t: Throwable) {
                Log.e("RoomDao", "Error deleting universities", t)
            }
        }
    }

}