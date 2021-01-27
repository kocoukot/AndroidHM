package com.example.androidhomework.threading

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.androidhomework.R
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
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
        }
    }

    private fun bindViewModels() {
        requestButton.setOnClickListener {
            viewModel.requestMovies()
        }

        viewModel.movies.observe(viewLifecycleOwner) { movieAdapter?.items = it }

        viewModel.showToast
            .observe(viewLifecycleOwner) {
                Handler().postDelayed({
                    Toast.makeText(requireContext(), "Список обновлен ", Toast.LENGTH_SHORT).show()
                }, 1000)
            }


    }
}