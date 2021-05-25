
package com.example.androidhomework.material.adapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomework.R
import com.example.androidhomework.inflate
import com.example.androidhomework.material.ItemShop
import com.example.androidhomework.roomdao.data.entities.Faculties
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_faculty.*
import kotlinx.android.synthetic.main.item_shop.*
import kotlinx.android.synthetic.main.item_uni.deleteUni

class ItemShopDelegate : AbsListItemAdapterDelegate<ItemShop, ItemShop, ItemShopDelegate.CommonHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup): CommonHolder {
        return CommonHolder(
            parent.inflate(R.layout.item_shop)
        )
    }

    override fun isForViewType(
        item: ItemShop,
        items: MutableList<ItemShop>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onBindViewHolder(
        item: ItemShop,
        holder: CommonHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }


    class CommonHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {

        }

        fun bind(itemShop: ItemShop) {
            itemImage.setImageResource(itemShop.itemImage)
            itemNameTextView.text = itemShop.itemName
            itemPriceTextView.text = itemShop.itemPrice

        }
    }
}