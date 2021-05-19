package com.example.androidhomework.files

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

class FilesRepository(
    private val context: Context
) {

    fun startDownLoad(url: String) {
        val workData = workDataOf(
            DownloadWorker.DOWNLOAD_URL_KEY to url
        )

        val workConstraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresBatteryNotLow(true)
            .build()

        val workRequest = OneTimeWorkRequestBuilder<DownloadWorker>()
            .setBackoffCriteria(BackoffPolicy.LINEAR, 20, TimeUnit.SECONDS)
            .setInputData(workData)
            .setConstraints(workConstraints)
            .build()

        WorkManager.getInstance(context)
            .enqueueUniqueWork(DOWNLOAD_KEY, ExistingWorkPolicy.KEEP, workRequest)
    }

    fun cancelDownload() {
        WorkManager.getInstance(context).cancelUniqueWork(DOWNLOAD_KEY)
    }

    companion object {
        const val DOWNLOAD_KEY = "download key"
    }

}
