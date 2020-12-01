package com.example.androidhomework.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidhomework.R
import kotlinx.android.extensions.LayoutContainer

/*
abstract class BaseAnimalHolder(
   override val containerView: View,
    onItemClicked: (position: Int) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private val imageView: ImageView = view.findViewById(R.id.rare_imageView)
    private val animalName: TextView = view.findViewById(R.id.rare_animalName)
    private val animalType: TextView = view.findViewById(R.id.common_animalType)

    init {
        containerView.setOnClickListener {
            onItemClicked(adapterPosition)
        }
    }

    protected fun bindMainInfo(
        name: String,
        imageLink: String,
        familyType: String
    ){

        animalName.text = name
        animalType.text = familyType
        Glide.with(itemView)
            .load(imageLink)
            .error(R.drawable.ic_sentiment)
            .placeholder(R.drawable.ic_local_florist)
            .into(itemView.imageView)
    }
}
*/
