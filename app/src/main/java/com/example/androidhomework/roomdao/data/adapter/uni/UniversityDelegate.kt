package com.example.androidhomework.roomdao.data.adapter.uni

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomework.R
import com.example.androidhomework.inflate
import com.example.androidhomework.roomdao.data.entities.Student
import com.example.androidhomework.roomdao.data.entities.University
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_student.*
import kotlinx.android.synthetic.main.item_uni.*

class UniversityDelegate(
    private val onItemClickedDelete: (position: Int) -> Unit,
    private val onItemClicked: (position: Int) -> Unit
) : AbsListItemAdapterDelegate<University, University, UniversityDelegate.CommonHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup): CommonHolder {
        return CommonHolder(
            parent.inflate(R.layout.item_uni),
            onItemClickedDelete,
            onItemClicked
        )
    }

    override fun isForViewType(
        item: University,
        items: MutableList<University>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onBindViewHolder(
        item: University,
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

        fun bind(uni: University) {
            uniName.text = uni.uniName
            uniAddress.text = uni.uniAddress

        }
    }
}