package com.example.androidhomework.contacts.detailed

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.example.androidhomework.R
import com.example.androidhomework.contacts.data.Contact
import kotlinx.android.synthetic.main.fragment_contact_detailed.*
import permissions.dispatcher.PermissionRequest
import permissions.dispatcher.ktx.constructPermissionsRequest

class ContactDetailedFragment : Fragment(R.layout.fragment_contact_detailed) {

    private val viewModel: ContactDetailedViewModel by viewModels()

    private val args: ContactDetailedFragmentArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Handler(Looper.getMainLooper()).post {
            constructPermissionsRequest(
                android.Manifest.permission.WRITE_CONTACTS,
                onShowRationale = ::onContactPermissionShowRationale,
                onPermissionDenied = ::onContactPermissionDenied,
                onNeverAskAgain = ::onContactPermissionNeverAsk,
                requiresPermission = { viewModel.getContact(args.contactId) }
            ).launch()
        }
        bindViewModels()
    }

    private fun bindViewModels() {
        viewModel.contact.observe(viewLifecycleOwner, ::setContactInfo)
        viewModel.isDeleted.observe(viewLifecycleOwner, ::contactDeleted)
        deletContactButton.setOnClickListener {
            viewModel.deleteContact(args.contactId)
        }
    }

    private fun contactDeleted(unit: Unit) {
        activity?.onBackPressed()
    }

    private fun setContactInfo(contact: Contact) {
        contactDetailedPhones.text = ""
        contactDetailedEmails.text = ""
        contactDetailedName.text = contact.name
        contact.number.forEach { number ->
            contactDetailedPhones.text = "${contactDetailedPhones.text} $number\n"
        }
        contact.mail.forEach { email ->
            contactDetailedEmails.text = "${contactDetailedEmails.text} $email\n"
        }
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