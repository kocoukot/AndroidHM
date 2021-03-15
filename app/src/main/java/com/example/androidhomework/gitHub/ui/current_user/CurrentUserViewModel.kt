package com.example.androidhomework.gitHub.ui.current_user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomework.gitHub.data.GitUser
import kotlinx.coroutines.*


class CurrentUserViewModel : ViewModel() {
    private val currentUserRepository = CurrentUserRepository()

    private val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->

        Log.e("module23", "error from CoroutineExceptionHandler", throwable)
        isLoadingLiveData.postValue(false)
        isErrorLiveData.postValue(true)
    }

    private val scope = CoroutineScope(Dispatchers.Default + errorHandler)

    private val currentUserLiveData = MutableLiveData<GitUser>()
    private val followingUsersLiveData = MutableLiveData<List<GitUser>>()

    private val isLoadingLiveData = MutableLiveData<Boolean>()
    private val isErrorLiveData = MutableLiveData<Boolean>()

    val currentUser: LiveData<GitUser>
        get() = currentUserLiveData

    val followingUsers: LiveData<List<GitUser>>
        get() = followingUsersLiveData

    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    val isError: LiveData<Boolean>
        get() = isErrorLiveData


    fun getCurrentUser() {
        scope.async {
            //  try {
            isLoadingLiveData.postValue(true)
            isErrorLiveData.postValue(false)

            val gitUser = currentUserRepository.getCurrentUserInfo()

            currentUserLiveData.postValue(gitUser)
            val following = currentUserRepository.getFollowing()
            followingUsersLiveData.postValue(following)
            isLoadingLiveData.postValue(false)
        }
    }

}