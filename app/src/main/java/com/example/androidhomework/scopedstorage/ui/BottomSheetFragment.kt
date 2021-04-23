package com.example.androidhomework.scopedstorage.ui

import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidhomework.R
import com.example.androidhomework.animals.AnimalsListFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_bottom_sheet.*
import kotlinx.android.synthetic.main.fragment_bottom_sheet.view.*

class BottomSheetFragment: BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_sheet, container,false)
        val button = view.downLoadVideo
        view.videoURLDownload.setText("https://drive.google.com/u/0/uc?id=1natc9AbtCq_nNzWW_QplHriPA5XIiRrf&export=download")
        button.setOnClickListener {
            (requireParentFragment() as VideoListFragment).downloadVideoByURL(
                videoNameNew.text.toString(),
                videoURLDownload.text.toString()
            )
            dismiss()
        }

        return view
    }
}