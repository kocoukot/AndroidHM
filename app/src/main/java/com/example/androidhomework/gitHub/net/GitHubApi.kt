package com.example.androidhomework.gitHub.net

import com.example.androidhomework.gitHub.data.GitUser
import com.example.androidhomework.gitHub.data.RemoteRepository
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface GitHubApi {
    @GET("user")
    fun searchUsers(): retrofit2.Call<GitUser>

    @GET("repositories")
    fun searchRepos(): retrofit2.Call<List<RemoteRepository>>

    @GET("repos/{owner}/{name}")
    fun searchRepoDetailed(
        @Path("owner") owner: String,
        @Path("name") name: String
        ): retrofit2.Call<RemoteRepository>

    @GET("user/starred/{owner}/{repo}")
    fun checkIfStared(
        @Path("owner") owner: String,
        @Path("repo") name: String
    ): retrofit2.Call<Boolean>

    @PUT("user/starred/{owner}/{repo}")
    fun starRepo(
        @Path("owner") owner: String,
        @Path("repo") name: String
    ): retrofit2.Call<Boolean>

    @DELETE("user/starred/{owner}/{repo}")
    fun unstarRepo(
        @Path("owner") owner: String,
        @Path("repo") name: String
    ): retrofit2.Call<Boolean>


}