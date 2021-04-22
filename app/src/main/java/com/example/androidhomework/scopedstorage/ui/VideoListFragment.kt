package com.example.androidhomework.scopedstorage.ui

import android.app.Activity
import android.app.RemoteAction
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidhomework.R
import com.example.androidhomework.hasQ
import com.example.androidhomework.scopedstorage.adapters.VideoAdapter
import kotlinx.android.synthetic.main.fragment_video_files.*

class VideoListFragment : Fragment(R.layout.fragment_video_files) {

    private val viewModel: VideoListViewModel by viewModels()
    private var videoListAdapter: VideoAdapter? = null

    private lateinit var requestPermissionLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var recoverableActionLauncher: ActivityResultLauncher<IntentSenderRequest>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().title = "Video list"
        initPermissionResultListener()
        initRecoverableActionListener()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
        initList()
        if (hasPermission().not()) {
            requestPermissions()
        } else {
            viewModel.permissionsGranted()
        }
    }

    private fun initList() {
        videoListAdapter = VideoAdapter { positionToDelete ->
            viewModel.deleteVideo(positionToDelete)
        }
        with(videosRecycler) {
            adapter = videoListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.videoList.observe(viewLifecycleOwner) { videoListAdapter?.items = it }
        viewModel.recoverableActionLiveData.observe(viewLifecycleOwner, ::handleRecoverableAction)

    }

    private fun hasPermission(): Boolean {
        return PERMISSIONS.all {
            ActivityCompat.checkSelfPermission(
                requireContext(),
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun requestPermissions() {
        requestPermissionLauncher.launch(PERMISSIONS.toTypedArray())
    }

    private fun initPermissionResultListener() {
        requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissionToGrantedMap: Map<String, Boolean> ->
            if (permissionToGrantedMap.values.all { it }) {
                viewModel.permissionsGranted()
            } else {
                viewModel.permissionsDenied()
            }
        }
    }

    private fun initRecoverableActionListener() {
        recoverableActionLauncher = registerForActivityResult(
            ActivityResultContracts.StartIntentSenderForResult()
        ) { activityResult ->
            val isConfirmed = activityResult.resultCode == Activity.RESULT_OK

            if (isConfirmed) {
                viewModel.confirmDelete()
            } else {
                viewModel.declineDelete()
            }
        }
    }


    fun downloadVideoByURL(name: String, url: String) {
        viewModel.saveVideo(name, url)
    }

    private fun bindViews() {
        addVideo.setOnClickListener {
            val bottomSheet = BottomSheetFragment()
            bottomSheet.show(childFragmentManager, "TAG")
        }
    }

    private fun handleRecoverableAction(remoteAction: RemoteAction) {

        val request = IntentSenderRequest.Builder(remoteAction.actionIntent.intentSender)
            .build()
        recoverableActionLauncher.launch(request)

    }

    companion object {
        private val PERMISSIONS = listOfNotNull(
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                .takeIf { hasQ().not() }
        )
    }
}