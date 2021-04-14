package com.example.androidhomework.roomdao.ui.studentslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomework.roomdao.data.entities.Student
import com.example.androidhomework.roomdao.ui.StudentRepository
import kotlinx.coroutines.launch

class StudentListViewModel : ViewModel() {
    private val studentRepository = StudentRepository()

    private val studentListLiveData = MutableLiveData<List<Student>>()


    val studentList: LiveData<List<Student>>
        get() = studentListLiveData


    fun getAllStudents() {
        viewModelScope.launch {
            try {
                studentListLiveData.postValue(studentRepository.getAllStudents())
            } catch (t: Throwable) {
                Log.e("RoomDao", "Error getting students", t)
            }
        }
    }

    fun addStudent(student: Student) {

    }

    fun getAllStudentsWithUnis(){
        viewModelScope.launch {
            try {
                studentRepository.getAllStudentsWithUnis()
            } catch (t: Throwable) {
                Log.e("RoomDao", "Error getting students", t)
            }
        }
    }


}