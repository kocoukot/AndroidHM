package com.example.androidhomework.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidhomework.Animals
import com.example.androidhomework.R
import com.example.androidhomework.inflate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class AnimalAdapter(
    private val onItemClicked: (position: Int) -> Unit
) : AsyncListDifferDelegationAdapter<Animals>(AnimalsDiffUtilCallBack()) {

    init {
        delegatesManager.addDelegate(RareAnimalDelegate(onItemClicked))
            .addDelegate(CommonAnimalDelegate(onItemClicked))
    }


    class AnimalsDiffUtilCallBack : DiffUtil.ItemCallback<Animals>() {
        override fun areItemsTheSame(oldItem: Animals, newItem: Animals): Boolean {
            return when {
                oldItem is Animals.Rare && newItem is Animals.Rare -> oldItem.id == newItem.id
                oldItem is Animals.Common && newItem is Animals.Common -> oldItem.id == newItem.id
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: Animals, newItem: Animals): Boolean {
            return oldItem == newItem
        }
    }

}