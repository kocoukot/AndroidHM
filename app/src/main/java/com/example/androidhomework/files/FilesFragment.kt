package com.example.androidhomework.files

import android.content.Context
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.work.*
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.androidhomework.R
import com.example.androidhomework.databinding.FragmentFilesBinding
import kotlinx.android.synthetic.main.animal_dialog.*
import kotlinx.android.synthetic.main.fragment_files.*
import kotlinx.coroutines.*
import java.io.File
import java.util.concurrent.TimeUnit

class FilesFragment : Fragment(R.layout.fragment_files) {

    private val testURL1 = "https://raw.githubusercontent.com/kean/Nuke/master/README.md"
    private val testURL2 = "https://github.com/Juanpe/SkeletonView/raw/main/README.md"

    private val binding by viewBinding(FragmentFilesBinding::bind)

   lateinit var repo: FilesRepository

    private val sharedPref by lazy {
        requireContext().getSharedPreferences(
            SHARED_PREF,
            Context.MODE_PRIVATE
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        urlEditText.setText(testURL2)
        repo = FilesRepository(requireContext())

        if (!sharedPref.getBoolean(IS_FIRST_LOAD_KEY, false)) {
            sharedPref.edit().putBoolean(IS_FIRST_LOAD_KEY, true).apply()
        }

        WorkManager.getInstance(requireContext())
            .getWorkInfosForUniqueWorkLiveData(FilesRepository.DOWNLOAD_KEY)
            .observe(viewLifecycleOwner) { handleWorkInfo(it.first()) }

        binding.buttonDownload.setOnClickListener {
            repo.startDownLoad(binding.urlEditText.text.toString())
        }

        binding.buttonDownloadCancel.setOnClickListener {
            repo.cancelDownload()
        }
    }

    private fun handleWorkInfo(workInfo: WorkInfo) {

        val isFinished = workInfo.state.isFinished

        binding.buttonDownload.isEnabled = isFinished
        binding.urlEditText.isEnabled = isFinished
        binding.loadingProgress.isVisible = isFinished.not()
        binding.buttonDownloadCancel.isVisible = isFinished.not()

        if (isFinished) {
            showToast("work is finished with state ${workInfo.state}")
        }
    }

    private fun showToast(toastText: String) {
        Toast.makeText(requireContext(), toastText, Toast.LENGTH_SHORT).show()
    }


    companion object {
        const val SHARED_PREF = "shared_pref_KEY"
        const val IS_FIRST_LOAD_KEY = "first_load_key"
    }
}