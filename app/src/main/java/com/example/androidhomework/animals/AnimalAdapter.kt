package com.example.androidhomework.animals

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class AnimalAdapter(
    private val onItemClicked: (name: String, family: String) -> Unit,
    private val onLongItemClicked: (position: Int) -> Unit

) : AsyncListDifferDelegationAdapter<Animals>(AnimalsDiffUtilCallBack()) {

    init {
        delegatesManager.addDelegate(RareAnimalDelegate(onItemClicked, onLongItemClicked))
            .addDelegate(CommonAnimalDelegate(onItemClicked,onLongItemClicked))
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