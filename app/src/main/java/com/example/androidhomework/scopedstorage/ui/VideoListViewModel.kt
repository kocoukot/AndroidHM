package com.example.androidhomework.scopedstorage.ui

import android.app.Application
import android.app.RecoverableSecurityException
import android.app.RemoteAction
import android.util.Log
import androidx.lifecycle.*
import com.example.androidhomework.hasQ
import com.example.androidhomework.scopedstorage.VideoListRepository
import com.example.androidhomework.scopedstorage.data.VideoData
import kotlinx.coroutines.launch

class VideoListViewModel(application: Application) : AndroidViewModel(application) {

    private val videoListRepository = VideoListRepository(application)

    private val videoListLiveData = MutableLiveData<List<VideoData>>()

    private val recoverableActionMutableLiveData = MutableLiveData<RemoteAction>()

    private var pendingDeleteId: Int? = null

    private var isObservingStarted: Boolean = false

    val videoList: LiveData<List<VideoData>>
        get() = videoListLiveData

    val recoverableActionLiveData: LiveData<RemoteAction>
        get() = recoverableActionMutableLiveData

    override fun onCleared() {
        super.onCleared()
        videoListRepository.unregisterObserver()
        isObservingStarted = false
    }

    fun permissionsGranted() {
        getAllVideos()
        if (!isObservingStarted) {
            videoListRepository.observeVideos { getAllVideos() }
            isObservingStarted = true
        }
    }

    fun permissionsDenied() {
        Log.e("Scoped Storage", "Permission denied")
    }

    private fun getAllVideos() {
        viewModelScope.launch {
            try {
                videoListLiveData.postValue(videoListRepository.getAllVideos())
            } catch (t: Throwable) {
                videoListLiveData.postValue(emptyList())
                Log.e("Scoped Storage", "Error getting videos", t)
            }
        }
    }

    fun saveVideo(name: String, url: String) {
        viewModelScope.launch {
            try {
                videoListRepository.saveVideo(name, url)
            } catch (t: Throwable) {

                Log.e("Scoped Storage", "Error downloading videos", t)
            }
        }
    }

    fun deleteVideo(position: Int) {
        viewModelScope.launch {
            try {
                videoListRepository.deleteVideo(videoListLiveData.value!![position].id)
                pendingDeleteId = null
            } catch (t: Throwable) {
                Log.e("Scoped Storage", "Error deleting video", t)
                if (hasQ() && t is RecoverableSecurityException) {
                    pendingDeleteId = position
                    recoverableActionMutableLiveData.postValue(t.userAction)
                } else {
                    Log.e("Scoped Storage", "Error deleting video", t)
                }
            }
        }
    }

    fun confirmDelete() {
        pendingDeleteId?.let {
            deleteVideo(it)
        }
    }

    fun declineDelete() {
        pendingDeleteId = null
    }


}