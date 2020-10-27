package com.example.androidhomework

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.updateLayoutParams
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.blank_fragment.*
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    private var state: FormState = FormState(valid = true, message = "")
    private val KEY_PARC = "ParcKey"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolBar()

        if (savedInstanceState != null) {
            state =
                savedInstanceState.getParcelable<FormState>(KEY_PARC) ?: error("Unexpected state")
        }
        fragmentAdd()
    }

    private fun fragmentAdd() {
            supportFragmentManager.beginTransaction()
                .add(R.id.frame_Layout, LoginFragment())
                .commit()
    }


    fun initToolBar() {
        toolBar.setNavigationOnClickListener {
            Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show()
        }
        toolBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_1 -> {
                    toastShow("action1")
                    true
                }
                R.id.action_2 -> {
                    toastShow("action2")
                    true
                }
                R.id.action_3 -> {
                    toastShow("action3")
                    true
                }
                else -> false
            }
        }

        val searchItem = toolBar.menu.findItem(R.id.searchToolBar)
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                toastShow("Expanded")
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                toastShow("Collapsed")
                return true
            }
        })

        (searchItem.actionView as SearchView).setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                toastShow("Submitted search $query")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                toastShow("New text $newText")
                return true
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_PARC, state)
    }

    private fun toastShow(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

}
