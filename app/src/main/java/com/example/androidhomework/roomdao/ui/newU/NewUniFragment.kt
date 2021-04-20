package com.example.androidhomework.roomdao.ui.newU

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.example.androidhomework.R
import kotlinx.android.synthetic.main.fragment_new_contact.*
import kotlinx.android.synthetic.main.fragment_new_contact.createButton
import kotlinx.android.synthetic.main.fragment_new_student.*
import kotlinx.android.synthetic.main.fragment_new_university.*

class NewUniFragment : Fragment(R.layout.fragment_new_university) {

    private val viewModel: NewUniversityViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindViewModels()
        requireActivity().title = "New University adding"
    }

    private fun bindViewModels() {
        addUniButton.setOnClickListener {
            viewModel.addUni(
                name = uniNameNew.text.toString(),
                address = uniAddressNew.text.toString()
            )
        }
        viewModel.isCreated.observe(viewLifecycleOwner, ::savedContact)
    }

    private fun savedContact(t: Unit) {
        activity?.onBackPressed()
    }
}