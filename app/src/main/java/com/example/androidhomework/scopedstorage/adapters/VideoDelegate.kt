package com.example.androidhomework.scopedstorage.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidhomework.R
import com.example.androidhomework.inflate
import com.example.androidhomework.scopedstorage.data.VideoData
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_following_user.*
import kotlinx.android.synthetic.main.item_video.*

class VideoDelegate(
    private val onItemClicked: (position: Int) -> Unit
) : AbsListItemAdapterDelegate<VideoData, VideoData, VideoDelegate.CommonHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): CommonHolder {
        return CommonHolder(parent.inflate(R.layout.item_video), onItemClicked)
    }

    override fun isForViewType(
        item: VideoData,
        items: MutableList<VideoData>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onBindViewHolder(item: VideoData, holder: CommonHolder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    class CommonHolder(
        override val containerView: View,
        onItemClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        init {
            deleteVideo.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }

        fun bind(video: VideoData) {
            videoName.text = video.name
            videoSize.text = video.size.toString()
            Glide.with(videoPreview)
                .load(video.uri)
                .error(R.drawable.ic_sentiment)
                .placeholder(R.drawable.ic_location_on)
                .into(videoPreview)

        }
    }
}