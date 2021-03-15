package com.example.androidhomework.gitHub.net

import com.example.androidhomework.gitHub.data.GitUser
import com.example.androidhomework.gitHub.data.RemoteRepository
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface GitHubApi {
    @GET("user")
    suspend fun searchUsers(): GitUser

    @GET("user/following")
    suspend fun serachFollowing(): List<GitUser>

    @GET("repositories")
   suspend fun searchRepos(): List<RemoteRepository>

    @GET("repos/{owner}/{name}")
    suspend fun searchRepoDetailed(
        @Path("owner") owner: String,
        @Path("name") name: String
        ): RemoteRepository

    @GET("user/starred/{owner}/{repo}")
    fun checkIfStared(
        @Path("owner") owner: String,
        @Path("repo") name: String
    ): Call<Boolean>

    @PUT("user/starred/{owner}/{repo}")
   suspend fun starRepo(
        @Path("owner") owner: String,
        @Path("repo") name: String
    ): Call<Boolean>

    @DELETE("user/starred/{owner}/{repo}")
    suspend fun unstarRepo(
        @Path("owner") owner: String,
        @Path("repo") name: String
    ): Call<Boolean>


}