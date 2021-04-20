package com.example.androidhomework.roomdao.ui.newFaculty

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomework.animals.SingleLiveEvent
import com.example.androidhomework.roomdao.data.bd.YearsToLearn
import com.example.androidhomework.roomdao.data.entities.Faculties
import com.example.androidhomework.roomdao.data.entities.University
import com.example.androidhomework.roomdao.ui.FacultyRepository
import com.example.androidhomework.roomdao.ui.UniversityRepository
import kotlinx.coroutines.launch

class NewFacultyViewModel : ViewModel() {

    private val facultyRepository = FacultyRepository()

    private val isCreatedLiveData = SingleLiveEvent<Unit>()

    val isCreated: LiveData<Unit>
        get() = isCreatedLiveData

    fun addFaculty(
        facultyName: String,
        facultyAddress: String,
        uniId:Long,
        uniName: String,
        uniAddress: String
    ) {

       val year =  YearsToLearn.values()[(0..YearsToLearn.values().size).random()]
        val faculty = Faculties(
            id = 0,
            facultyName = facultyName,
            facultyAddress = facultyAddress,
            yearsToLearn = year,
            facultyUniId = uniId,
            facultyUniName = uniName,
            facultyUniAddress = uniAddress
        )
        viewModelScope.launch {
            try {
                facultyRepository.addFaculty(faculty)
                isCreatedLiveData.postValue(Unit)
            } catch (t: Throwable) {
                Log.e("RoomDao", "Error creating faculty", t)
            }
        }

    }


}