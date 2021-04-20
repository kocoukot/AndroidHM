package com.example.androidhomework.roomdao.data.adapter.faculty

import androidx.recyclerview.widget.DiffUtil
import com.example.androidhomework.roomdao.data.entities.Faculties
import com.example.androidhomework.roomdao.data.entities.Student
import com.example.androidhomework.roomdao.data.entities.University
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class FacultyAdapter(
    private val onItemClickedDelete: (position: Int) -> Unit,
    private val onItemClicked: (position: Int) -> Unit
) : AsyncListDifferDelegationAdapter<Faculties>(FacultiesDiffUtilCallBack()) {
    init {
        delegatesManager.addDelegate(
            FacultyDelegate(
                onItemClickedDelete,
                onItemClicked
            )
        )
    }
}

class FacultiesDiffUtilCallBack : DiffUtil.ItemCallback<Faculties>() {
    override fun areItemsTheSame(
        oldItem: Faculties,
        newItem: Faculties
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Faculties,
        newItem: Faculties
    ): Boolean {
        return oldItem == newItem
    }

}