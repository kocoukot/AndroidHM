package com.example.androidhomework.threading

import androidx.recyclerview.widget.DiffUtil
import com.example.androidhomework.animals.AnimalAdapter
import com.example.androidhomework.animals.Animals
import com.example.androidhomework.animals.CommonAnimalDelegate
import com.example.androidhomework.animals.RareAnimalDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class MovieListAdapter: AsyncListDifferDelegationAdapter<Movie>(MoviesDiffUtilCallBack()) {

    init {
        delegatesManager.addDelegate(MovieDelegateAdapter())
    }


    class MoviesDiffUtilCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.movieId == newItem.movieId
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

}