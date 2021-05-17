package com.example.androidhomework.threading.ui

import android.os.Bundle

import android.util.Log

import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidhomework.R
import com.example.androidhomework.threading.*
import kotlinx.android.synthetic.main.fragment_threading.*
import kotlinx.coroutines.flow.map

import kotlinx.coroutines.launch


class ThreadingFragment : Fragment(R.layout.fragment_threading) {

    private val viewModel: ThreadingViewModel by viewModels()

    private var movieAdapter: MovieListAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initList()
        bindViewModels()
        progressBar.isVisible = false
    }

    private fun initList() {
        movieAdapter = MovieListAdapter()
        with(moviesListRecyclerView) {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val dividerItemDecoration =
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)

        }
    }

    private fun bindViewModels() {
        viewLifecycleOwner.lifecycleScope.launch {
            val name = filmNameEditText.textChangedFlow()
            val type = radioMovie.selectedRadioChangedFlow()
                .map {
                    when (it){
                        "Movie" -> MovieType.MOVIE
                        "Series" -> MovieType.SERIES
                        else -> MovieType.EPISODE
                    }
                }

            viewModel.getMovieFlow(name, type)
        }



        viewModel.isLoading.observe(viewLifecycleOwner, ::updateLoadingState)
        viewModel.movies.observe(viewLifecycleOwner) { movieAdapter?.items = it }
        viewModel.isError.observe(viewLifecycleOwner, :: errorLoading)
    }

    private fun errorLoading(isError: Boolean){
        loadingErrorText.isVisible = isError
        if (isError){
            progressBar.isVisible = isError.not()
            moviesListRecyclerView.isVisible = isError.not()
        }

    }

    private fun updateLoadingState(isLoading: Boolean) {
        Log.d("module21", "статус загрузки $isLoading")
        filmNameEditText.isEnabled = isLoading.not()
        loadingErrorText.isVisible = false
        moviesListRecyclerView.isVisible = isLoading.not()
        progressBar.isVisible = isLoading
    }
}