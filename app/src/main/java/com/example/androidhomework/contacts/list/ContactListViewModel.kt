package com.example.androidhomework.contacts.list

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.androidhomework.contacts.data.Contact
import kotlinx.coroutines.launch

class ContactListViewModel(application: Application) : AndroidViewModel(application) {

    private val contactRepository = ContactListRepository(application)

    private val contactsListLiveData = MutableLiveData<List<Contact>>()

    val contactList: LiveData<List<Contact>>
        get() = contactsListLiveData

    fun getContacts() {
        viewModelScope.launch {
            try {
                contactsListLiveData.postValue(contactRepository.getContacts())
            } catch (t: Throwable) {
                Log.e("Module25", "contact list error", t)
                contactsListLiveData.postValue(emptyList())
            }
        }

    }
}