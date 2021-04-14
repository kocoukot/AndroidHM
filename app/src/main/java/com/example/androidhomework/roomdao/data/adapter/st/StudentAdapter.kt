package com.example.androidhomework.roomdao.data.adapter.st

import androidx.recyclerview.widget.DiffUtil
import com.example.androidhomework.roomdao.data.entities.Student
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class StudentAdapter(
    private val onItemClicked: (positionL: Int) -> Unit
) : AsyncListDifferDelegationAdapter<Student>(StudentsDiffUtilCallBack()) {
    init {
        delegatesManager.addDelegate(
            StudentDelegate(
                onItemClicked
            )
        )
    }
}

class StudentsDiffUtilCallBack : DiffUtil.ItemCallback<Student>() {
    override fun areItemsTheSame(
        oldItem: Student,
        newItem: Student
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Student,
        newItem: Student
    ): Boolean {
        return oldItem == newItem
    }

}