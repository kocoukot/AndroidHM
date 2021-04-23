package com.example.androidhomework.scopedstorage.data

import android.net.Uri

data class VideoData(
    val id: Long,
    val uri: Uri,
    val name: String,
    val size: Int
)