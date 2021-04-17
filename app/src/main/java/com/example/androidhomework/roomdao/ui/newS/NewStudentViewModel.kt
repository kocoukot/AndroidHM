package com.example.androidhomework.roomdao.ui.newS

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomework.animals.SingleLiveEvent
import com.example.androidhomework.roomdao.data.entities.Student
import com.example.androidhomework.roomdao.ui.StudentRepository
import kotlinx.coroutines.launch

class NewStudentViewModel : ViewModel() {

    private val studentRepository =
        StudentRepository()

    private val isCreatedLiveData = SingleLiveEvent<Unit>()

    val isCreated: LiveData<Unit>
        get() = isCreatedLiveData


    fun addStudent(name: String, age: Int, phone: Int, uniName: String, uniAddress: String) {
        viewModelScope.launch {
            try {
                studentRepository.addStudent(name,age, phone, uniName, uniAddress)

             //   studentRepository.addStudent(student, uniName, uniAddress)
                isCreatedLiveData.postValue(Unit)
            } catch (t: Throwable) {
                Log.e("RoomDao", "Error adding student", t)
            }
        }

    }


}