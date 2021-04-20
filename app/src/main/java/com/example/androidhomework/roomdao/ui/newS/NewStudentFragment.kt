package com.example.androidhomework.roomdao.ui.newS

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.example.androidhomework.R
import com.example.androidhomework.roomdao.ui.universities.UniversityLIstViewModel
import kotlinx.android.synthetic.main.fragment_new_contact.*
import kotlinx.android.synthetic.main.fragment_new_contact.createButton
import kotlinx.android.synthetic.main.fragment_new_student.*

class NewStudentFragment : Fragment(R.layout.fragment_new_student) {

    private val viewModel: NewStudentViewModel by viewModels()
    private val viewModelUnis: UniversityLIstViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindViewModels()
    }

    private fun bindViewModels() {
        viewModelUnis.universityList.observe(viewLifecycleOwner) {
            var uniNameList = it.map { name -> name.uniName }
            if (uniNameList.isEmpty()) {
                uniNameList = listOf("Список универсотетов пуст")
                createButton.isEnabled = false
            }
            val adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, uniNameList)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            uniSpinner.adapter = adapter
        }
        viewModelUnis.getAllUnis()

        createButton.setOnClickListener {
            val selectedUni = uniSpinner.selectedItemPosition
            viewModel.addStudent(
                name = studentNameNew.text.toString(),
                age = studentAgeNew.text.toString().toInt(),
                phone = studentPhoneNew.text.toString().toInt(),
                selectedUni = selectedUni
            )
        }
        viewModel.isCreated.observe(viewLifecycleOwner, ::savedContact)
    }

    private fun savedContact(t: Unit) {
        activity?.onBackPressed()
    }
}