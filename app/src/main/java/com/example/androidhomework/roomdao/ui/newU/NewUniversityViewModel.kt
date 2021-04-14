package com.example.androidhomework.roomdao.ui.newU

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomework.animals.SingleLiveEvent
import com.example.androidhomework.roomdao.data.entities.University
import com.example.androidhomework.roomdao.ui.UniversityRepository
import kotlinx.coroutines.launch

class NewUniversityViewModel : ViewModel() {

    private val uniRepository = UniversityRepository()

    private val isCreatedLiveData = SingleLiveEvent<Unit>()

    val isCreated: LiveData<Unit>
        get() = isCreatedLiveData


    fun addUni(name: String, address: String) {
        val university = University(uniName = name, uniAddress = address)
        viewModelScope.launch {
            try {
                uniRepository.addUni(university)
                isCreatedLiveData.postValue(Unit)
            } catch (t: Throwable) {

            }
        }

    }


}