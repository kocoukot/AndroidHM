package com.example.androidhomework.contacts.newcontact

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.example.androidhomework.R
import kotlinx.android.synthetic.main.fragment_new_contact.*
import permissions.dispatcher.PermissionRequest
import permissions.dispatcher.ktx.constructPermissionsRequest

class NewContactFragment : Fragment(R.layout.fragment_new_contact) {

    private val viewModel: NewContactViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindViewModels()
        initEditText()

        Handler(Looper.getMainLooper()).post {
            constructPermissionsRequest(
                android.Manifest.permission.WRITE_CONTACTS,
                onShowRationale = ::onContactPermissionShowRationale,
                onPermissionDenied = ::onContactPermissionDenied,
                onNeverAskAgain = ::onContactPermissionNeverAsk,
                requiresPermission = {}
            ).launch()
        }
    }

    private fun bindViewModels() {
        createButton.setOnClickListener {
            viewModel.createContact(
                name = "${secondName.text} ${firstName.text}",
                phone = numberNew.text.toString(),
                email = emailNew.text.toString()
            )
        }
        viewModel.isCreated.observe(viewLifecycleOwner, ::savedContact)
    }

    private fun savedContact(t: Unit) {
        activity?.onBackPressed()
    }

    private fun initEditText() {
        listOf(secondName, firstName, numberNew).forEach { field ->
            field.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    buttonStatusCheck()
                }
            })
        }
    }

    private fun buttonStatusCheck() {
        createButton.isEnabled =
            secondName.text.isNotEmpty() && firstName.text.isNotEmpty() && numberNew.text.isNotEmpty()
    }

    private fun onContactPermissionDenied() {
        Toast.makeText(requireContext(), "Denied", Toast.LENGTH_SHORT).show()
    }

    private fun onContactPermissionShowRationale(request: PermissionRequest) {
        Toast.makeText(requireContext(), "Work", Toast.LENGTH_SHORT).show()
        request.proceed()
    }

    private fun onContactPermissionNeverAsk() {
        Toast.makeText(requireContext(), "Never ask", Toast.LENGTH_SHORT).show()
    }

}