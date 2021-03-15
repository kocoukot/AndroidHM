package com.example.androidhomework.gitHub.ui.repository_list

import android.util.Log
import com.example.androidhomework.gitHub.net.NetworkGit
import com.example.androidhomework.gitHub.data.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryListRepository {

    suspend fun getRepos(): List<RemoteRepository> {
        return withContext(Dispatchers.Default) { NetworkGit.githubApi.searchRepos() }
    }


}

