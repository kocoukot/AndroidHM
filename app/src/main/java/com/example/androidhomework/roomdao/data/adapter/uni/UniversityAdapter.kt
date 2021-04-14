package com.example.androidhomework.roomdao.data.adapter.uni

import androidx.recyclerview.widget.DiffUtil
import com.example.androidhomework.roomdao.data.entities.Student
import com.example.androidhomework.roomdao.data.entities.University
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class UniversityAdapter(
    private val onItemClickedDelete: (position: Int) -> Unit,
    private val onItemClicked: (position: Int) -> Unit
) : AsyncListDifferDelegationAdapter<University>(UniversitiesDiffUtilCallBack()) {
    init {
        delegatesManager.addDelegate(
            UniversityDelegate(
                onItemClickedDelete,
                onItemClicked
            )
        )
    }
}

class UniversitiesDiffUtilCallBack : DiffUtil.ItemCallback<University>() {
    override fun areItemsTheSame(
        oldItem: University,
        newItem: University
    ): Boolean {
        return oldItem.uniAddress == newItem.uniAddress
    }

    override fun areContentsTheSame(
        oldItem: University,
        newItem: University
    ): Boolean {
        return oldItem == newItem
    }

}