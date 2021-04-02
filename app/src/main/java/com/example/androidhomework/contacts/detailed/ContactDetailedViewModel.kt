package com.example.androidhomework.contacts.detailed

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.androidhomework.animals.SingleLiveEvent
import com.example.androidhomework.contacts.data.Contact
import kotlinx.coroutines.launch

class ContactDetailedViewModel (application: Application) : AndroidViewModel(application) {

    private val contactRepository = ContactDetailedRepository(application)

    private val contactLiveData = MutableLiveData<Contact>()

    private val isDeletedLiveData = SingleLiveEvent<Unit>()

    val contact: LiveData<Contact>
        get() = contactLiveData

    val isDeleted: LiveData<Unit>
        get() = isDeletedLiveData


    fun getContact(contactId: Long) {
        viewModelScope.launch {
            try {
                contactLiveData.postValue(contactRepository.getContact(contactId))
            } catch (t: Throwable) {
                Log.e("Module25", "contact load error", t)
            }
        }
    }

    fun deleteContact(contactId: Long) {
        viewModelScope.launch {
            try {
                contactRepository.deleteContact(contactId)
                isDeletedLiveData.postValue(Unit)
            } catch (t: Throwable) {
                Log.e("Module25", "contact delete error", t)
            }
        }
    }
}