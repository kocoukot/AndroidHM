package com.example.androidhomework.moshi

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.androidhomework.R
import com.example.androidhomework.threading.MovieListAdapter
import kotlinx.android.synthetic.main.fragment_moshi.*
import kotlinx.android.synthetic.main.fragment_threading.*
import kotlinx.android.synthetic.main.item_movie.*


class ThreadingFragmentMoshi : Fragment(R.layout.fragment_moshi) {

    private val viewModel: ThreadingViewModelMoshi by viewModels()
   // private val


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindViewModels()
        progressBarMoshi.isVisible = false
    }


    private fun bindViewModels() {

        requestButtonMoshi.setOnClickListener {

            val movieTitle = filmNameMoshi.text.toString()
            viewModel.requestMovies(movieTitle)
        }

        tryAgainButtonMoshi.setOnClickListener {
            val movieTitle = filmNameMoshi.text.toString()
            viewModel.requestMovies(movieTitle)
        }

        addRatingButtonMoshi.setOnClickListener {
            viewModel.addRating()
//            movieRatingMoshi.text = "${movieRatingMoshi.text}Personal rating - ${(0..10).random()}/10"
        }


        viewModel.isLoading.observe(viewLifecycleOwner, ::updateLoadingState)
        viewModel.movies.observe(viewLifecycleOwner) { infoSet(it) }
        viewModel.isError.observe(viewLifecycleOwner, ::errorLoading)
        viewModel.isFound.observe(viewLifecycleOwner, ::isMovieFound)
    }

    private fun infoSet(movie: MovieMoshi) {

        movieNameMoshi.text = movie.title
        movieDateMoshi.text = movie.year
        movieTypeMoshi.text = movie.rated.toString()
        movieIDMoshi.text = movie.genre
        Glide.with(movie_imageViewMoshi)
            .load(movie.poster)
            .error(R.drawable.ic_sentiment)
            .placeholder(R.drawable.ic_local_florist)
            .into(movie_imageViewMoshi)
        var rate = ""
        for (rating in movie.ratings){
            rate += "${rating.key} - ${rating.value} \n"
        }

        movieRatingMoshi.text = rate


    }

    private fun errorLoading(isError: Boolean) {
        loadingErrorTextMoshi.text = "error loading"
        loadingErrorTextMoshi.isVisible = isError
        tryAgainButtonMoshi.isVisible = isError

        if (isError) {
            addRatingButtonMoshi.isEnabled = isError.not()
            movieInfo.isVisible = isError.not()
            progressBarMoshi.isVisible = isError.not()
        }
    }

    private fun isMovieFound(isFound: Boolean) {
        addRatingButtonMoshi.isEnabled = isFound
        loadingErrorTextMoshi.text = "no film found"
        loadingErrorTextMoshi.isVisible = isFound.not()
        movieInfo.isVisible = isFound

    }

    private fun updateLoadingState(isLoading: Boolean) {
        Log.d("module21", "статус загрузки $isLoading")
        movieInfo.isVisible = isLoading.not()
        addRatingButtonMoshi.isEnabled = isLoading.not()
        loadingErrorTextMoshi.isVisible = false
        tryAgainButtonMoshi.isVisible = false
        progressBarMoshi.isVisible = isLoading
        requestButtonMoshi.isEnabled = isLoading.not()
    }
}