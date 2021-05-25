package com.example.androidhomework

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.androidhomework.material.CartFragment
import com.example.androidhomework.material.FavouriteFragment
import com.example.androidhomework.material.ProfileFragment
import com.example.androidhomework.material.ShopFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main_material.*


class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.shop -> {
                val fragment =
                    ShopFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fragmentMaterial, fragment)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.cart -> {
                val fragment =
                    CartFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fragmentMaterial, fragment)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.profile -> {
                val fragment =
                    ProfileFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fragmentMaterial, fragment)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.favorites -> {
                val fragment =
                    FavouriteFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fragmentMaterial, fragment)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_material)

        if (savedInstanceState == null) {
            val fragment = ShopFragment()
            supportFragmentManager.beginTransaction().replace(R.id.fragmentMaterial, fragment)
                .commit()
        }




        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }


}