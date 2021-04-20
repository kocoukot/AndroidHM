package com.example.androidhomework.roomdao.ui.studentslist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidhomework.R
import com.example.androidhomework.roomdao.data.adapter.st.StudentAdapter
import kotlinx.android.synthetic.main.fragment_students.*

class StudentListFragment: Fragment(R.layout.fragment_students) {

    private val viewModel: StudentListViewModel by viewModels()

    private var studentAdapter: StudentAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().title = "Students list"

        bindViewModels()
        initList()
    }

    private fun initList() {
        studentAdapter =
            StudentAdapter { id ->
//            val contactId = viewModel.contactList.value?.get(id)?.id ?: 0
//            val actions =
//                ContactsListFragmentDirections.actionFragmentContactsListToFragmentContactDetailed(
//                    contactId
//                )
//            findNavController().navigate(actions)
            }

        with(studentstListRecycler) {
            adapter = studentAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.studentList.observe(viewLifecycleOwner) { studentAdapter?.items = it }
        viewModel.getAllStudents()
        viewModel.getAllStudentsWithUnis()

    }

    private fun bindViewModels() {
        addStudent.setOnClickListener {
            val action =
                StudentListFragmentDirections.actionStudentListFragmentToNewStudentFragment()
            findNavController().navigate(action)
        }
    }
}
