package com.example.androidhomework.contacts.list.listadapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomework.R
import com.example.androidhomework.contacts.data.Contact
import com.example.androidhomework.inflate
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_contact_list.*

class ContactDelegate
    (
    private val onItemClicked: (contactId: Int) -> Unit
) :
    AbsListItemAdapterDelegate<Contact, Contact, ContactDelegate.CommonHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): CommonHolder {
        return CommonHolder(
            parent.inflate(R.layout.item_contact_list), onItemClicked
        )
    }

    override fun isForViewType(
        item: Contact,
        items: MutableList<Contact>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onBindViewHolder(
        item: Contact,
        holder: CommonHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class CommonHolder(
        override val containerView: View,
        onItemClicked: (contactId: Int) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {
            containerView.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }

        fun bind(contact: Contact) {
            contactName.text = contact.name
            contact.number.forEach { number ->
                contactNumber.text = "${contactNumber.text} $number\n"
            }

            contact.mail.forEach { email ->
                contactMail.text = "${contactMail.text} $email\n"
            }
        }
    }
}