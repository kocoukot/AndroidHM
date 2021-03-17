package com.example.androidhomework.gitHub.ui.repo

import android.util.Log
import com.example.androidhomework.gitHub.net.NetworkGit
import com.example.androidhomework.gitHub.data.RemoteRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class RepositoryDetailedRepository {


    suspend fun getRepo(
        owner: String,
        name: String
    ): RemoteRepository {
        return NetworkGit.githubApi.searchRepoDetailed(owner, name)
    }

    suspend fun checkIfStared(
        owner: String,
        name: String
    ): Boolean {
        return suspendCancellableCoroutine { continuation ->
            NetworkGit.githubApi.checkIfStared(owner, name).enqueue(object : Callback<Boolean> {
                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    continuation.resumeWithException(t)
                    Log.d("module22", "error message ${t.message}")
                }

                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                    if (response.isSuccessful) {
                        continuation.resume(true)
                    } else {
                        Log.d("module22", "error response on check if stared or not stared")
                    }
                }

            })
        }
    }

    suspend fun starRepo(
        owner: String,
        name: String,
        callback: (Boolean) -> Unit,
        errorCallBack: (e: Throwable) -> Unit
    ) {
        NetworkGit.githubApi.starRepo(owner, name).enqueue(object : Callback<Boolean> {
            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                errorCallBack(t)
                Log.d("module23", "error message ${t.message}")
            }

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.isSuccessful) {
                    callback(true)
                } else {
                    Log.d("module23", "error response on check if stared or not stared")
                }
            }
        })
    }

    suspend fun unstarRepo(
        owner: String,
        name: String,
        callback: (Boolean) -> Unit,
        errorCallBack: (e: Throwable) -> Unit
    ) {
        NetworkGit.githubApi.unstarRepo(owner, name).enqueue(object : Callback<Boolean> {
            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                errorCallBack(t)
                Log.d("module23", "error message ${t.message}")
            }

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.isSuccessful) {
                    callback(false)
                } else {
                    Log.d("module23", "error response on check if stared or not stared")
                }
            }
        })
    }

}