package com.example.androidhomework.roomdao.data.adapter.faculty

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomework.R
import com.example.androidhomework.inflate
import com.example.androidhomework.roomdao.data.entities.Faculties
import com.example.androidhomework.roomdao.data.entities.Student
import com.example.androidhomework.roomdao.data.entities.University
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_faculty.*
import kotlinx.android.synthetic.main.item_student.*
import kotlinx.android.synthetic.main.item_uni.*
import kotlinx.android.synthetic.main.item_uni.deleteUni

class FacultyDelegate(
    private val onItemClickedDelete: (position: Int) -> Unit,
    private val onItemClicked: (position: Int) -> Unit
) : AbsListItemAdapterDelegate<Faculties, Faculties, FacultyDelegate.CommonHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup): CommonHolder {
        return CommonHolder(
            parent.inflate(R.layout.item_faculty),
            onItemClickedDelete,
            onItemClicked
        )
    }

    override fun isForViewType(
        item: Faculties,
        items: MutableList<Faculties>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onBindViewHolder(
        item: Faculties,
        holder: CommonHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }


    class CommonHolder(
        override val containerView: View,
        onItemClickedDelete: (position: Int) -> Unit,
        onItemClicked: (position: Int) -> Unit

    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {

            deleteUni.setOnClickListener {
                onItemClickedDelete(adapterPosition)
            }

            containerView.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }

        fun bind(faculty: Faculties) {
            facultyName.text = faculty.facultyName
            facultyAddress.text = faculty.facultyAddress

        }
    }
}