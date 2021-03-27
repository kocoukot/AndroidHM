package com.example.androidhomework.contacts.list

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidhomework.R
import com.example.androidhomework.contacts.list.listadapter.ContactAdapter
import kotlinx.android.synthetic.main.fragment_contacts_list.*
import permissions.dispatcher.PermissionRequest
import permissions.dispatcher.ktx.constructPermissionsRequest

class ContactsListFragment : Fragment(R.layout.fragment_contacts_list) {
    private val viewModel: ContactListViewModel by viewModels()

    private var contactAdapter: ContactAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Handler(Looper.getMainLooper()).post {
            constructPermissionsRequest(
                android.Manifest.permission.READ_CONTACTS,
                onShowRationale = ::onContactPermissionShowRationale,
                onPermissionDenied = ::onContactPermissionDenied,
                onNeverAskAgain = ::onContactPermissionNeverAsk,
                requiresPermission = { viewModel.getContacts() }
            ).launch()
        }
        bindViewModels()
        initList()

    }

    private fun bindViewModels() {
        addContact.setOnClickListener {
            val actions =
                ContactsListFragmentDirections.actionFragmentContactsListToNewContactFragment()
            findNavController().navigate(actions)
        }
        viewModel.contactList.observe(viewLifecycleOwner) {
            contactAdapter?.items = it
        }
    }

    private fun initList() {
        contactAdapter = ContactAdapter { id ->
            val contactId = viewModel.contactList.value?.get(id)?.id ?: 0
            val actions =
                ContactsListFragmentDirections.actionFragmentContactsListToFragmentContactDetailed(
                    contactId
                )
            findNavController().navigate(actions)
        }
        with(contactListRecycler) {
            adapter = contactAdapter
            layoutManager = LinearLayoutManager(requireContext())
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