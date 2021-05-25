package com.example.androidhomework.material.adapter

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.example.androidhomework.material.ItemShop
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class ItemAdapter : AsyncListDifferDelegationAdapter<ItemShop>(
    ItemShopDiffUtilCallBack()
) {

    init {
        delegatesManager.addDelegate(ItemShopDelegate())
    }


    class ItemShopDiffUtilCallBack : DiffUtil.ItemCallback<ItemShop>() {
        override fun areItemsTheSame(oldItem: ItemShop, newItem: ItemShop): Boolean {
            Log.d("module32", "get here ${oldItem.id == newItem.id} ")
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ItemShop, newItem: ItemShop): Boolean {
            return oldItem == newItem
        }
    }

}