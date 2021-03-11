package com.example.androidhomework.gitHub.ui.repo

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.androidhomework.R
import com.example.androidhomework.gitHub.data.GitUser
import com.example.androidhomework.gitHub.data.RemoteRepository
import com.example.androidhomework.gitHub.ui.repository_list.RepositoryListViewModel
import kotlinx.android.synthetic.main.fragment_current_user.*
import kotlinx.android.synthetic.main.fragment_repository_detailed.*
import kotlinx.android.synthetic.main.fragment_repository_list.*

class RepositoryDetailedFragment : Fragment(R.layout.fragment_repository_detailed) {

    private val viewModel: RepositoryDetailedViewModel by viewModels()

    private var stared = false
    private val args: RepositoryDetailedFragmentArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindViewModel()
    }

    private fun bindViewModel() {
        viewModel.isLoading.observe(viewLifecycleOwner, ::updateLoadingState)
        viewModel.isError.observe(viewLifecycleOwner, ::errorLoading)
        viewModel.repos.observe(viewLifecycleOwner) { infoSet(it) }
        viewModel.isStared.observe(viewLifecycleOwner, ::checkIfStared)
        viewModel.requestRepos(args.repoOwner, args.repoName)

        repoStar.setOnClickListener {
            if (this.stared){
                viewModel.unstarRepo(args.repoOwner, args.repoName)
            } else {
                viewModel.starRepo(args.repoOwner, args.repoName)
            }

        }
    }

    private fun updateLoadingState(isLoading: Boolean) {
        repoDetailed.isVisible = isLoading.not()
        repoDetailedLoading.isVisible = isLoading
    }

    private fun errorLoading(isError: Boolean) {
        repoDetailedLoadingErrorText.isVisible = isError
        repoDetailedTryAgainButton.isVisible = isError
        if (isError) {
            repoDetailed.isVisible = isError.not()
            repoDetailedLoading.isVisible = isError.not()
        }
    }

    private fun infoSet(repo: RemoteRepository) {
        repoDetailedFullName.text = repo.fullName
        repoDetailedOwner.text = repo.owner.login
        repoDetailedDescription.text = "Description: ${repo.description}"
        repoDetailedLanguage.text = "Language ${repo.language}"
    }

    private fun checkIfStared(stared: Boolean){
        this.stared = stared
        if (stared) {
            repoStar.setImageResource(R.drawable.ic_baseline_star)
        } else {
            repoStar.setImageResource(R.drawable.ic_baseline_star_outline)
        }
    }
}