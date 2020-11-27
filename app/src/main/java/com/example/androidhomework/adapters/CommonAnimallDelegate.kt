package com.example.androidhomework.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidhomework.Animals
import com.example.androidhomework.R
import com.example.androidhomework.inflate
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_common.*
import kotlinx.android.synthetic.main.item_common.*

class CommonAnimalDelegate(
    private val onItemClicked: (position: Int) -> Unit
): AbsListItemAdapterDelegate<Animals.Common, Animals, CommonAnimalDelegate.CommonHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): CommonHolder {
        return CommonHolder(
            parent.inflate(R.layout.item_common),
            onItemClicked)
    }

    override fun isForViewType(item: Animals, items: MutableList<Animals>, position: Int): Boolean {
        return item is Animals.Common
    }

    override fun onBindViewHolder(
        item: Animals.Common,
        holder: CommonHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class CommonHolder(
        override val containerView: View,
        onItemClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {
            containerView.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }

        fun bind(animal: Animals.Common) {
            common_animalName.text = animal.name
            common_animalType.text = animal.familyType
            Glide.with(itemView)
                .load(animal.imageLink)
                .error(R.drawable.ic_sentiment)
                .placeholder(R.drawable.ic_local_florist)
                .into(common_imageView)
        }
    }
}