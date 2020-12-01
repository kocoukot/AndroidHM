package com.example.androidhomework.location

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class LocationAdapter(
    private val onItemClicked: (position: Int) -> Unit
) : AsyncListDifferDelegationAdapter<Location>(LocationsDiffUtilCallBack()) {

    init {
        delegatesManager.addDelegate(LocationDelegate(onItemClicked))

    }

    class LocationsDiffUtilCallBack : DiffUtil.ItemCallback<Location>() {
        override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
            return when {
                oldItem is Location.GotLocation && newItem is Location.GotLocation -> oldItem.id == newItem.id
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem == newItem
        }
    }

}