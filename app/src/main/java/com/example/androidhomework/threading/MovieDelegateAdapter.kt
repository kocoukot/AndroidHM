package com.example.androidhomework.threading

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidhomework.R
import com.example.androidhomework.inflate
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.*

class MovieDelegateAdapter: AbsListItemAdapterDelegate<Movie, Movie, MovieDelegateAdapter.CommonHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): CommonHolder {
        return CommonHolder(
            parent.inflate(R.layout.item_movie))
    }

    override fun isForViewType(item: Movie, items: MutableList<Movie>, position: Int): Boolean {
        return true
    }

    override fun onBindViewHolder(
        item: Movie,
        holder: CommonHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class CommonHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {


        fun bind(movie: Movie) {
            movieName.text = movie.title
            movieDate.text = movie.year
            movieType.text = movie.type
            movieID.text = movie.movieId
            Glide.with(itemView)
                .load(movie.poster)
                .error(R.drawable.ic_sentiment)
                .placeholder(R.drawable.ic_local_florist)
                .into(movie_imageView)
        }
    }
}