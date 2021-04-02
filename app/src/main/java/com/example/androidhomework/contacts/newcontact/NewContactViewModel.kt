package com.example.androidhomework.contacts.newcontact

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.androidhomework.animals.SingleLiveEvent
import kotlinx.coroutines.launch

class NewContactViewModel(application: Application) : AndroidViewModel(application) {

    private val newContactRepository = NewContactRepository(application)

    private val isCreatedLiveData = SingleLiveEvent<Unit>()

    val isCreated: LiveData<Unit>
        get() = isCreatedLiveData

    fun createContact(name: String, phone: String, email: String) {
        viewModelScope.launch {
            try {
                newContactRepository.saveContact(name, phone, email)
                isCreatedLiveData.postValue(Unit)
            } catch (t: Throwable) {
                Log.d("module25", "contact add error", t)
            }

        }
    }
}