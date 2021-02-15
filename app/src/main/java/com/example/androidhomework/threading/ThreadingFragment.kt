package com.example.androidhomework.threading

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidhomework.R
import kotlinx.android.synthetic.main.fragment_threading.*


class ThreadingFragment : Fragment(R.layout.fragment_threading) {

    private val viewModel: ThreadingViewModel by viewModels()

    private var movieAdapter: MovieListAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initList()
        bindViewModels()


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
        val adapter = ArrayAdapter.createFromResource(
            requireContext(), R.array.film_types, android.R.layout.simple_expandable_list_item_1
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        dropDownMenu.setAdapter(adapter)


        requestButton.setOnClickListener {

            val movieTitle = filmNameEditText.text.toString()
            val movieYear = filmYearEditText.text.toString()
            val movieType = dropDownMenu.text.toString().let {
                if (it == "not selected") {
                    ""
                } else {
                    it
                }
            }
            viewModel.requestMovies(movieTitle, movieYear, movieType)
        }

        tryAgainButton.setOnClickListener {

            val movieTitle = filmNameEditText.text.toString()
            val movieYear = filmYearEditText.text.toString()
            val movieType = dropDownMenu.text.toString().let {
                if (it == "not selected") {
                    ""
                } else {
                    it
                }
            }
            viewModel.requestMovies(movieTitle, movieYear, movieType)
        }

        viewModel.isLoading.observe(viewLifecycleOwner, ::updateLoadingState)
        viewModel.movies.observe(viewLifecycleOwner) { movieAdapter?.items = it }
        viewModel.isError.observe(viewLifecycleOwner, :: errorLoading)
    }

    private fun errorLoading(isError: Boolean){
        loadingErrorText.isVisible = isError
        tryAgainButton.isVisible = isError
        progressBar.isVisible = isError.not()

    }

    private fun updateLoadingState(isLoading: Boolean) {
        Log.d("module21", "статус загрузки $isLoading")
        filmNameEditText.isEnabled = isLoading.not()
        filmYearEditText.isEnabled = isLoading.not()
        loadingErrorText.isVisible = false
        tryAgainButton.isVisible = false
        menu.isEnabled = isLoading.not()
        moviesListRecyclerView.isVisible = isLoading.not()
        progressBar.isVisible = isLoading
        requestButton.isEnabled = isLoading.not()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}