package com.example.androidhomework

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AnimalAdapter(
    private val onItemClicked: (position: Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var animals: List<Animals> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType){
            TYPE_RARE -> RareHolder(parent.inflate(R.layout.item_rare), onItemClicked)
            TYPE_COMMON-> CommonHolder(parent.inflate(R.layout.item_common), onItemClicked)
            else -> error("Incorrect viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (animals[position]) {
            is Animals.Rare -> TYPE_RARE
            is Animals.Common -> TYPE_COMMON
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder){
            is RareHolder -> {
                val animal  = animals[position].let { it as? Animals.Rare} ?: error("Not correct animal")
                holder.bind(animal)
            }
            is CommonHolder -> {
                val animal  = animals[position].let { it as? Animals.Common} ?: error("Not correct animal")
                holder.bind(animal)
            }
            else -> {
                error ("Incorrect view Holder")
            }
        }
    }

    fun updateAnimals(newAnimal: List<Animals>) {
        animals = newAnimal
    }

    override fun getItemCount(): Int {
        return animals.size
    }

    abstract class BaseAnimalHolder(
        view: View,
        onItemClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        private val imageView: ImageView = view.findViewById(R.id.imageView)
        private val animalName: TextView = view.findViewById(R.id.animalName)
        private val animalType: TextView = view.findViewById(R.id.animalType)

        init {
            view.setOnClickListener {
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
                .into(imageView)
        }
    }


    class RareHolder(
        view: View,
        onItemClicked: (position: Int) -> Unit
    ) : BaseAnimalHolder(view, onItemClicked) {

        private val food: TextView = view.findViewById(R.id.foodType)

        fun bind(animal: Animals.Rare) {
            food.text = animal.rarity
            bindMainInfo(animal.name, animal.imageLink, animal.familyType)
        }
    }


    class CommonHolder(
        view: View,
        onItemClicked: (position: Int) -> Unit
    ) : BaseAnimalHolder(view, onItemClicked) {

        init {
            view.findViewById<TextView>(R.id.foodType).isVisible = false
        }

        fun bind(animal: Animals.Common) {
            bindMainInfo(animal.name, animal.imageLink, animal.familyType)
        }
    }

    companion object {
        private val TYPE_RARE = 1
        private val TYPE_COMMON = 2

    }

}