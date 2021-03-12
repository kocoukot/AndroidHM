package com.example.androidhomework.gitHub.ui.current_user

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.example.androidhomework.R
import com.example.androidhomework.gitHub.data.GitUser
import kotlinx.android.synthetic.main.fragment_current_user.*

class CurrentUserFragment: Fragment(R.layout.fragment_current_user) {

    private val viewModel: CurrentUserViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindViewModel()
    }

    private fun bindViewModel() {
        viewModel.getCurrentUser()
        viewModel.isLoading.observe(viewLifecycleOwner, ::updateLoadingState)
        viewModel.isError.observe(viewLifecycleOwner, ::errorLoading)
        viewModel.currentUser.observe(viewLifecycleOwner) { infoSet(it) }
        tryAgainButton.setOnClickListener { viewModel.getCurrentUser() }
    }

    private fun infoSet(user: GitUser) {
        userName.text = user.name
        userLogin.text = user.login
        userID.text = user.id.toString()
        userFollowers.text = "Followers ${user.followers}"
        userfollowing.text = "Following ${user.following}"
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
            userInfo.isVisible = isError.not()
            userLoading.isVisible = isError.not()
        }
    }




}