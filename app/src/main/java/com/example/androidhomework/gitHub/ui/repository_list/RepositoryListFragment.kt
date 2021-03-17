package com.example.androidhomework.gitHub.ui.repository_list

import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidhomework.R
import com.example.androidhomework.gitHub.ui.repository_list.repo_adapter.RepoListAdapter
import kotlinx.android.synthetic.main.fragment_repository_list.*

class RepositoryListFragment : Fragment(R.layout.fragment_repository_list) {

    private val viewModel: RepositoryListViewModel by viewModels()

    private var reposAdapter: RepoListAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initList()
        bindViewModel()
    }

    private fun initList() {
        reposAdapter = RepoListAdapter {owner, name ->
            val actions = RepositoryListFragmentDirections.actionRepositoryListFragmentToRepositoryDetailedFragment(owner, name)
            findNavController().navigate(actions)
        }
        with(repositoryList) {
            adapter = reposAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val dividerItemDecoration =
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)

        }
    }

    private fun bindViewModel() {
        viewModel.isLoading.observe(viewLifecycleOwner, ::updateLoadingState)
        viewModel.isError.observe(viewLifecycleOwner, ::errorLoading)
        viewModel.repos.observe(viewLifecycleOwner) { reposAdapter?.items = it}
        viewModel.requestRepos()
        reposTryAgainButton.setOnClickListener {
            viewModel.requestRepos()
        }
    }

    private fun updateLoadingState(isLoading: Boolean) {
        repositoryList.isVisible = isLoading.not()
        reposLoading.isVisible = isLoading
    }

    private fun errorLoading(isError: Boolean) {
       reposLoadingErrorText.isVisible = isError
        reposTryAgainButton.isVisible = isError

        if (isError) {
            repositoryList.isVisible = isError.not()
            reposLoading.isVisible = isError.not()
        }
    }


}