package com.example.androidhomework.roomdao.data.adapter.st

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomework.R
import com.example.androidhomework.inflate
import com.example.androidhomework.roomdao.data.entities.Student
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_student.*

class StudentDelegate(
    private val onItemClicked: (positionL: Int) -> Unit
) : AbsListItemAdapterDelegate<Student, Student, StudentDelegate.CommonHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup): CommonHolder {
        return CommonHolder(
            parent.inflate(R.layout.item_student),
            onItemClicked
        )
    }

    override fun isForViewType(
        item: Student,
        items: MutableList<Student>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onBindViewHolder(
        item: Student,
        holder: CommonHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }


    class CommonHolder(
        override val containerView: View,
        onItemClicked: (positionL: Int) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {
            containerView.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }

        fun bind(student: Student) {
        studentName.text = student.name
            studentAge.text = student.age.toString()
            studentPhoneNum.text = student.phoneNumber.toString()

        }
    }
}