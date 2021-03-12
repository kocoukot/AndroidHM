package com.example.androidhomework.gitHub.ui.current_user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidhomework.gitHub.data.GitUser


class CurrentUserViewModel : ViewModel() {
    private val currentUserRepository = CurrentUserRepository()

    private val currentUserLiveData = MutableLiveData<GitUser>()
    private val isLoadingLiveData = MutableLiveData<Boolean>()
    private val isErrorLiveData = MutableLiveData<Boolean>()

    val currentUser: LiveData<GitUser>
        get() = currentUserLiveData

    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    val isError: LiveData<Boolean>
        get() = isErrorLiveData

    fun getCurrentUser() {
        isLoadingLiveData.postValue(true)
        isErrorLiveData.postValue(false)
        currentUserRepository.getCurrentUserInfo({ gitUser ->
            currentUserLiveData.postValue(gitUser)
            isLoadingLiveData.postValue(false)
        }, {
            Log.d("module22", it.message)
            isLoadingLiveData.postValue(false)
            isErrorLiveData.postValue(true)
        })
    }

}