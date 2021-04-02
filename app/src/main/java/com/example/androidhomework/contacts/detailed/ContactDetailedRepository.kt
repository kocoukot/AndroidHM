package com.example.androidhomework.contacts.detailed

import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract
import android.util.Log
import com.example.androidhomework.contacts.data.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactDetailedRepository(
    private val context: Context
) {

    suspend fun getContact(contactId: Long): Contact? = withContext(Dispatchers.IO) {
        context.contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            "${ContactsContract.Contacts.NAME_RAW_CONTACT_ID} = ?",
            arrayOf(contactId.toString()),
            null
        )?.use {
            getContactsFromCursor(it, contactId)
        }
    }

    private fun getContactsFromCursor(cursor: Cursor, contactId: Long): Contact {
        if (cursor.moveToFirst().not()) return Contact(1, "", emptyList(), emptyList())

        val nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)
        val name = cursor.getString(nameIndex).orEmpty()

        return Contact(
            id = contactId,
            name = name,
            number = getPhonesForContact(contactId),
            mail = getEmailForContact(contactId)
        )
    }

    private fun getPhonesForContact(contactId: Long): List<String> {
        return context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            "${ContactsContract.CommonDataKinds.Phone.CONTACT_ID} = ?",
            arrayOf(contactId.toString()),
            null
        )?.use {
            getPhonesFromCursor(it)
        }.orEmpty()
    }

    private fun getPhonesFromCursor(cursor: Cursor): List<String> {
        if (cursor.moveToFirst().not()) return emptyList()
        val list = mutableListOf<String>()
        do {
            val numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            val number = cursor.getString(numberIndex).orEmpty()
            list.add(number)
        } while (cursor.moveToNext())
        return list
    }

    private fun getEmailForContact(contactId: Long): List<String> {
        return context.contentResolver.query(
            ContactsContract.CommonDataKinds.Email.CONTENT_URI,
            null,
            "${ContactsContract.CommonDataKinds.Email.CONTACT_ID} = ?",
            arrayOf(contactId.toString()),
            null
        )?.use {
            getEmailsFromCursor(it)
        }.orEmpty()
    }

    private fun getEmailsFromCursor(cursor: Cursor): List<String> {
        if (cursor.moveToFirst().not()) return emptyList()
        val list = mutableListOf<String>()
        do {
            val emailIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS)
            val email = cursor.getString(emailIndex).orEmpty()
            list.add(email)
        } while (cursor.moveToNext())
        return list
    }

    suspend fun deleteContact(contactId: Long) = withContext(Dispatchers.IO) {
        deleteRowContact(contactId)

    }


    private fun deleteRowContact(contactId: Long) {
        context.contentResolver.delete(
            ContactsContract.RawContacts.CONTENT_URI,
            "${ContactsContract.Contacts._ID} = ?",
            arrayOf(contactId.toString())
        )
    }
}