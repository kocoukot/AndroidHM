package com.example.androidhomework.scopedstorage.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.androidhomework.scopedstorage.data.VideoData
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class VideoAdapter(
    private val onItemClicked: (position: Int) -> Unit
) : AsyncListDifferDelegationAdapter<VideoData>(VideoListDiffUtilCallBack()) {
    init {
        delegatesManager.addDelegate(
            VideoDelegate(onItemClicked)
        )
    }
}

class VideoListDiffUtilCallBack : DiffUtil.ItemCallback<VideoData>() {
    override fun areItemsTheSame(oldItem: VideoData, newItem: VideoData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: VideoData, newItem: VideoData): Boolean {
        return oldItem == newItem
    }

}
