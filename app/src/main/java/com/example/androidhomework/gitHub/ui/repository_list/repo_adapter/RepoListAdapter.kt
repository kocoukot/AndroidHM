package com.example.androidhomework.gitHub.ui.repository_list.repo_adapter

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.example.androidhomework.gitHub.data.RemoteRepository
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class RepoListAdapter(
    private val onItemClicked: (owner: String, name: String) -> Unit
    ) :
    AsyncListDifferDelegationAdapter<RemoteRepository>(ReposDiffUtilCallBack()) {

    init {
        delegatesManager.addDelegate(RepoDelegateAdapter(onItemClicked))
    }


    class ReposDiffUtilCallBack : DiffUtil.ItemCallback<RemoteRepository>() {
        override fun areItemsTheSame(
            oldItem: RemoteRepository,
            newItem: RemoteRepository
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RemoteRepository,
            newItem: RemoteRepository
        ): Boolean {
            return oldItem == newItem
        }
    }

}
