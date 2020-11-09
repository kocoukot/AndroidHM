package com.example.androidhomework

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ArticleAdapter(
    private val screens: List<FragmentScreen>,
    activity: FragmentActivity
) : FragmentStateAdapter(activity) {


    override fun getItemCount(): Int {
        return screens.size
    }

    override fun createFragment(position: Int): Fragment {
        val screen: FragmentScreen = screens[position]
        return ArticleFragment.newInstance(
            articeTitle = screen.articeTitle,
            textRes = screen.textRes,
            image = screen.image,
            tag = screen.tag,
            isShown = screen.isShown
        )
    }

}