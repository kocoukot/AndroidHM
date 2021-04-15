package com.example.androidhomework.roomdao.ui.newFaculty

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.example.androidhomework.R
import com.example.androidhomework.roomdao.ui.faculties.FacultyListFragmentArgs
import kotlinx.android.synthetic.main.fragment_new_contact.*
import kotlinx.android.synthetic.main.fragment_new_contact.createButton
import kotlinx.android.synthetic.main.fragment_new_faculty.*
import kotlinx.android.synthetic.main.fragment_new_student.*
import kotlinx.android.synthetic.main.fragment_new_university.*

class NewFacultyFragment : Fragment(R.layout.fragment_new_faculty) {

    private val viewModel: NewFacultyViewModel by viewModels()

    private val args: NewFacultyFragmentArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindViewModels()
        requireActivity().title = "New Faculty adding"
    }

    private fun bindViewModels() {
        addFacButton.setOnClickListener {
            viewModel.addFaculty(
                facultyNameNew.text.toString(),
                facultyAddressNew.text.toString(),
                args.uniId,
                args.uniName,
                args.uniAddress
            )
        }
        viewModel.isCreated.observe(viewLifecycleOwner, ::savedContact)
    }

    private fun savedContact(t: Unit) {
        activity?.onBackPressed()
    }
}