package com.example.androidhomework.roomdao.ui.newS

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


    fun addStudent(name: String, age: Int, phone: Int,uniAddress: String, uniName: String) {
        val student = Student(id = 0,name = name,age =  age, gender = "male",phoneNumber =  phone,uniId = 1,uniAddress = uniAddress, uniName = uniName)
        viewModelScope.launch {
            try {
                studentRepository.addStudent(student)
                isCreatedLiveData.postValue(Unit)
            } catch (t: Throwable) {

            }
        }

    }


}