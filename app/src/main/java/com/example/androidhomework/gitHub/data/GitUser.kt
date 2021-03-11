package com.example.androidhomework.gitHub.data

import com.example.androidhomework.moshi.RatedMoshi
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GitUser(
    val name: String?,
    val login: String,
    val id: Int,
    @Json(name = "avatar_url")
    val avatar: String,
    val email: String?,
    val followers: Int = 0,
    val following: Int = 0
)
