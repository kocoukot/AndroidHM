package com.example.androidhomework

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.FragmentTransaction
//import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : Fragment(R.layout.main_fragment) {
    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        parentFragmentManager.beginTransaction().add(R.id.main_Fragment, ListFragment()).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }
}
