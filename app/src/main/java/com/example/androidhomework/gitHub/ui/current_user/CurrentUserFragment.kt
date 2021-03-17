package com.example.androidhomework.gitHub.ui.current_user

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.androidhomework.R
import com.example.androidhomework.gitHub.data.GitUser
import com.example.androidhomework.gitHub.ui.current_user.following_adapter.FollowingAdapter
import com.example.androidhomework.gitHub.ui.repository_list.RepositoryListFragmentDirections
import com.example.androidhomework.gitHub.ui.repository_list.repo_adapter.RepoListAdapter
import kotlinx.android.synthetic.main.fragment_current_user.*
import kotlinx.android.synthetic.main.fragment_repository_list.*

class CurrentUserFragment: Fragment(R.layout.fragment_current_user) {

    private val viewModel: CurrentUserViewModel by viewModels()
    private var followingAdapter: FollowingAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initList()
        bindViewModel()
    }

    private fun initList() {
        followingAdapter = FollowingAdapter()
        with(followingRecycleView) {
            adapter = followingAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val dividerItemDecoration =
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)

        }
    }

    private fun bindViewModel() {
        viewModel.getCurrentUser()
        viewModel.isLoading.observe(viewLifecycleOwner, ::updateLoadingState)
        viewModel.isError.observe(viewLifecycleOwner, ::errorLoading)
        viewModel.currentUser.observe(viewLifecycleOwner) { infoSet(it) }
        viewModel.followingUsers.observe(viewLifecycleOwner) { followingAdapter?.items = it}

        tryAgainButton.setOnClickListener { viewModel.getCurrentUser() }
    }

    private fun infoSet(user: GitUser) {
        userName.text = user.name
        userLogin.text = user.login
        userID.text = user.id.toString()
        userFollowers.text = "Followers ${user.followers}"
        userFollowing.text = "Following ${user.following}"
            Glide.with(userAvatar)
            .load(user.avatar)
            .error(R.drawable.ic_sentiment)
            .placeholder(R.drawable.ic_location_on)
            .into(userAvatar)
    }

    private fun updateLoadingState(isLoading: Boolean) {
        userInfo.isVisible = isLoading.not()
        userLoading.isVisible = isLoading

    }

    private fun errorLoading(isError: Boolean) {
        loadingErrorText.isVisible = isError
        tryAgainButton.isVisible = isError

        if (isError) {
            userFollowingText.isVisible = isError.not()
            userInfo.isVisible = isError.not()
            userLoading.isVisible = isError.not()
        }
    }




}