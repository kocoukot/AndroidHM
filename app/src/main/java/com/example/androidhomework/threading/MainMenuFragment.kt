package com.example.androidhomework.threading

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidhomework.R
import com.example.androidhomework.animals.AnimalsListFragmentDirections
import kotlinx.android.synthetic.main.fragment_main_menu.*

class MainMenuFragment: Fragment(R.layout.fragment_main_menu) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        buttonsInit()
    }

    private fun buttonsInit() {
        moviesButton.setOnClickListener {
           findNavController().navigate(R.id.action_threadingFragment_to_threadingFragment3)
        }

        raceConditionButton.setOnClickListener {
            findNavController().navigate(R.id.action_threadingFragment_to_raceConditionFragment)

        }

        deadLockButton.setOnClickListener {
            findNavController().navigate(R.id.action_threadingFragment_to_deadlockFragment2)

        }
    }
}