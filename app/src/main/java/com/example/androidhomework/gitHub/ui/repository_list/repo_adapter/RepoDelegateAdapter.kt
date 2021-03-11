package com.example.androidhomework.gitHub.ui.repository_list.repo_adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomework.R
import com.example.androidhomework.gitHub.data.RemoteRepository
import com.example.androidhomework.inflate
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_repository_list.*

class RepoDelegateAdapter(
    private val onItemClicked: (owner: String, name: String) -> Unit
) :
    AbsListItemAdapterDelegate<RemoteRepository, RemoteRepository, RepoDelegateAdapter.CommonHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): CommonHolder {
        return CommonHolder(
            parent.inflate(R.layout.item_repository_list), onItemClicked)
    }

    override fun isForViewType(
        item: RemoteRepository,
        items: MutableList<RemoteRepository>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onBindViewHolder(
        item: RemoteRepository,
        holder: CommonHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class CommonHolder(
        override val containerView: View,
        onItemClicked: (owner: String, name: String) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {
            containerView.setOnClickListener {
                onItemClicked(repOwner.text.toString(),repFullName.text.toString())
            }
        }

        fun bind(repo: RemoteRepository) {
            repFullName.text = repo.name
            repOwner.text = repo.owner.login
            repDescription.text = repo.description

        }
    }
}