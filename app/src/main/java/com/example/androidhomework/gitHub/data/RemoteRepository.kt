package com.example.androidhomework.gitHub.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteRepository(
    val id: Int = 0,
    val name: String,
    @Json(name = "full_name")
    val fullName: String,
    val owner: GitUser,
    val description: String?,
    val language: String?
)