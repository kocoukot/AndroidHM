package com.example.androidhomework

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayoutMediator
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.frameForListFragment, AnimalsListFragment(0))
                .commit()
        }



    }

    override fun onPause() {
        super.onPause()
        onSaveInstanceState(Bundle())
    }

    fun onTable(view: View) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameForListFragment, AnimalsListFragment(0))
            .commit()
    }
    fun onGrid(view: View) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameForListFragment, AnimalsListFragment(1))
            .commit()
    }
    fun onStaggered(view: View) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameForListFragment, AnimalsListFragment(2))
            .commit()
    }

}