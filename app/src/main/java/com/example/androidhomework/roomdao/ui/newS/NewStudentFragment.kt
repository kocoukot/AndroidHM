package com.example.androidhomework.roomdao.ui.newS

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.example.androidhomework.R
import kotlinx.android.synthetic.main.fragment_new_contact.*
import kotlinx.android.synthetic.main.fragment_new_contact.createButton
import kotlinx.android.synthetic.main.fragment_new_student.*

class NewStudentFragment : Fragment(R.layout.fragment_new_student) {

    private val viewModel: NewStudentViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindViewModels()
    }

    private fun bindViewModels() {
        createButton.setOnClickListener {
            viewModel.addStudent(
                name = studentNameNew.text.toString(),
                age = studentAgeNew.text.toString().toInt(),
                phone = studentPhoneNew.text.toString().toInt(),
                uniName = studentsUniName.text.toString(),
                uniAddress = studentsUniAddress.text.toString()
            )
        }
        viewModel.isCreated.observe(viewLifecycleOwner, ::savedContact)
    }

    private fun savedContact(t: Unit) {
        activity?.onBackPressed()
    }
}