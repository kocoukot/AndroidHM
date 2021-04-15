package com.example.androidhomework.roomdao.ui.universities

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidhomework.R
import com.example.androidhomework.roomdao.data.adapter.uni.UniversityAdapter
import kotlinx.android.synthetic.main.fragment_students.studentstListRecycler
import kotlinx.android.synthetic.main.fragment_universities.*
import kotlinx.android.synthetic.main.item_uni.*

class UniversityFragment : Fragment(R.layout.fragment_universities) {
    private val viewModel: UniversityLIstViewModel by viewModels()

    private var universityAdapter: UniversityAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().title = "University list"
        initList()
        bindViewModels()

    }

    private fun initList() {
        universityAdapter =
            UniversityAdapter({ positionDelete ->
                viewModel.deleteUni(positionDelete)
            }, { position ->
                val uniName = viewModel.universityList.value?.get(position)!!.uniName
                val uniAddress = viewModel.universityList.value?.get(position)!!.uniAddress
                val uniId = viewModel.universityList.value?.get(position)!!.uniId
                val actions = UniversityFragmentDirections.actionUniversityFragmentToFacultyListFragment(
                    uniName,
                    uniAddress,
                    uniId
                )

                findNavController().navigate(actions)
            })
        with(studentstListRecycler) {
            adapter = universityAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        viewModel.getAllUniWithRelations()
        viewModel.getAllUnisWithStudents()
        viewModel.universityList.observe(viewLifecycleOwner) { universityAdapter?.items = it }
        viewModel.getAllUnis()

    }

    private fun bindViewModels() {
        addUniversity.setOnClickListener {
            val action =
                UniversityFragmentDirections.actionUniversityFragmentToNewStudentFragment()
            findNavController().navigate(action)
        }
    }
}