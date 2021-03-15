package com.example.androidhomework.gitHub.ui.current_user.following_adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.androidhomework.gitHub.data.GitUser
import com.example.androidhomework.gitHub.data.RemoteRepository
import com.example.androidhomework.gitHub.ui.repository_list.repo_adapter.RepoDelegateAdapter
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class FollowingAdapter():
AsyncListDifferDelegationAdapter<GitUser>(ReposDiffUtilCallBack()) {

    init {
        delegatesManager.addDelegate(FollowingDelegateAdapter())
    }


    class ReposDiffUtilCallBack : DiffUtil.ItemCallback<GitUser>() {
        override fun areItemsTheSame(
            oldItem: GitUser,
            newItem: GitUser
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GitUser,
            newItem: GitUser
        ): Boolean {
            return oldItem == newItem
        }
    }
}