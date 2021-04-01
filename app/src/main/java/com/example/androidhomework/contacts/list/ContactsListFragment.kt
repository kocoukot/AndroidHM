package com.example.androidhomework.contacts.list

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidhomework.BuildConfig
import com.example.androidhomework.R
import com.example.androidhomework.contacts.list.listadapter.ContactAdapter
import com.example.androidhomework.files.data.FilesNetwork
import kotlinx.android.synthetic.main.activity_main_contacts.*
import kotlinx.android.synthetic.main.fragment_contacts_list.*
import kotlinx.android.synthetic.main.fragment_files.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import permissions.dispatcher.PermissionRequest
import permissions.dispatcher.ktx.constructPermissionsRequest
import java.io.File

class ContactsListFragment : Fragment(R.layout.fragment_contacts_list) {

    private val url = "https://raw.githubusercontent.com/kean/Nuke/master/README.md"
    var fileName = url.substring(url.lastIndexOf("/") + 1)

    private val viewModel: ContactListViewModel by viewModels()

    private var contactAdapter: ContactAdapter? = null

    private val fileScope = CoroutineScope(Dispatchers.IO)

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

        shareFile.setOnClickListener {
            downLoad()
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

    private fun downLoad() {
        val intStor = requireContext().getExternalFilesDir("saved_files")
        val file = File(intStor, fileName)
        fileScope.launch {
            try {
                file.outputStream().buffered().use { fileOutputStream ->
                    FilesNetwork.api.getFile(url)
                        .byteStream()
                        .use { inputStream ->
                            inputStream.copyTo(fileOutputStream)
                        }
                }
                withContext(Dispatchers.Main) {
                    showToast("File $fileName downloaded")
                }
                shareFile()
            } catch (t: Throwable) {
                file.delete()
                Log.d("module25", "error ${t.message}")
            }
        }
    }

    private fun shareFile() {
        lifecycleScope.launch(Dispatchers.IO) {
            val intStor = requireContext().getExternalFilesDir("saved_files")
            val file = File(intStor, fileName)
            if (file.exists().not()) return@launch

            val uri = FileProvider.getUriForFile(
                requireContext(),
                "${BuildConfig.APPLICATION_ID}.file_provider",
                file
            )
            Log.d("module25", uri.toString())

            val intent = Intent(Intent.ACTION_SEND).apply {
                putExtra(Intent.EXTRA_STREAM,uri)
                type = requireContext().contentResolver.getType(uri)
                setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }

            val share = Intent.createChooser(intent,null)
            startActivity(share)
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

    private fun showToast(toastText: String) {
        Toast.makeText(requireContext(), toastText, Toast.LENGTH_SHORT).show()
    }

}