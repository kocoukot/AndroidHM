package com.example.androidhomework.animals

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidhomework.R
import com.example.androidhomework.inflate
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_rare.*

class RareAnimalDelegate(
    private val onItemClicked: (position: Int) -> Unit
): AbsListItemAdapterDelegate<Animals.Rare, Animals, RareAnimalDelegate.RareHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup): RareHolder {
        return RareHolder(
            parent.inflate(R.layout.item_rare),
            onItemClicked)
    }

    override fun isForViewType(item: Animals, items: MutableList<Animals>, position: Int): Boolean {
        return item is Animals.Rare
    }

    override fun onBindViewHolder(
        item: Animals.Rare,
        holder: RareHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }


    class RareHolder(
        override val containerView: View,
        onItemClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {


        init {
            containerView.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }

        fun bind(animal: Animals.Rare) {
            rare_foodType.text = animal.rarity
            rare_animalName.text = animal.name
            rare_animalType.text = animal.familyType
            Glide.with(itemView)
                .load(animal.imageLink)
                .error(R.drawable.ic_sentiment)
                .placeholder(R.drawable.ic_local_florist)
                .into(rare_imageView)
        }
    }
}