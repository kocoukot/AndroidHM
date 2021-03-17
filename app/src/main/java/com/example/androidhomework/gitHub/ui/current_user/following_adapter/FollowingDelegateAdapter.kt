package com.example.androidhomework.gitHub.ui.current_user.following_adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidhomework.R
import com.example.androidhomework.gitHub.data.GitUser
import com.example.androidhomework.gitHub.data.RemoteRepository
import com.example.androidhomework.inflate
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_current_user.*
import kotlinx.android.synthetic.main.item_following_user.*
import kotlinx.android.synthetic.main.item_repository_list.*

class FollowingDelegateAdapter (): AbsListItemAdapterDelegate<GitUser, GitUser, FollowingDelegateAdapter.CommonHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): CommonHolder {
        return CommonHolder(
            parent.inflate(R.layout.item_following_user)
        )
    }

    override fun isForViewType(
        item: GitUser,
        items: MutableList<GitUser>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onBindViewHolder(
        item: GitUser,
        holder: CommonHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class CommonHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {


        fun bind(user: GitUser) {
            followingUserName.text = user.login
            Glide.with(followingUserAvatar)
                .load(user.avatar)
                .error(R.drawable.ic_sentiment)
                .placeholder(R.drawable.ic_location_on)
                .into(followingUserAvatar)
        }
    }
}