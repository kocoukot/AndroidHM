package com.example.androidhomework.roomdao.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidhomework.R
import kotlinx.android.synthetic.main.fragment_roomdao.*

class StartRoomDaoFragment: Fragment(R.layout.fragment_roomdao) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindViewModels()
    }

    private fun bindViewModels() {
        enterUniversities.setOnClickListener {
            val action = StartRoomDaoFragmentDirections.actionStartRoomDaoFragmentToUniversityFragment()
            findNavController().navigate(action)
        }

        enterStudents.setOnClickListener {
            val action = StartRoomDaoFragmentDirections.actionStartRoomDaoFragmentToStudentListFragment()
            findNavController().navigate(action)
        }
    }
}