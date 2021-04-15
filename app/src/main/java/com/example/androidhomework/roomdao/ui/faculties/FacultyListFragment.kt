package com.example.androidhomework.roomdao.ui.faculties

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidhomework.R
import com.example.androidhomework.gitHub.ui.repo.RepositoryDetailedFragmentArgs
import com.example.androidhomework.roomdao.data.adapter.faculty.FacultyAdapter
import com.example.androidhomework.roomdao.data.adapter.uni.UniversityAdapter
import kotlinx.android.synthetic.main.fragment_faculties.*
import kotlinx.android.synthetic.main.fragment_students.studentstListRecycler
import kotlinx.android.synthetic.main.fragment_universities.*
import kotlinx.android.synthetic.main.item_uni.*

class FacultyListFragment : Fragment(R.layout.fragment_faculties) {
    private val viewModel: FacultyListViewModel by viewModels()

    private var facultyAdapter: FacultyAdapter? = null

    private val args: FacultyListFragmentArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().title = "${args.uniName} Faculty List"
        bindViewModels()
        initList()
    }

    private fun initList() {
        facultyAdapter =
            FacultyAdapter({ positionDelete ->
                viewModel.deleteFaculty(positionDelete)
            }, {
            })

        with(facultyListRecycler) {
            adapter = facultyAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        viewModel.facultyList.observe(viewLifecycleOwner) { facultyAdapter?.items = it }
        viewModel.getAllFaculties(args.uniId)

    }

    private fun bindViewModels() {
        addFaculty.setOnClickListener {
            val action =
                FacultyListFragmentDirections.actionFacultyListFragmentToNewFacultyFragment(
                    args.uniName,
                    args.uniAddress,
                    args.uniId
                )
            findNavController().navigate(action)
        }
    }
}