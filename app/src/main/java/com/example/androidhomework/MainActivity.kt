package com.example.androidhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        initToolBar()




        Glide.with(this)
            .load("https://img.drive.ru/i/0/597705fdec05c4b315000004.jpg")
            .into(imageViewHead)

        loginEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                buttonStatusCheck()
            }
        })
        pasEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                buttonStatusCheck()
            }

        })

    }


    fun initToolBar(){
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
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener{
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {

                toastShow("Expanded")
                return true

            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                toastShow("Collapsed")
                return true
            }
        })

        (searchItem.actionView as SearchView).setOnQueryTextListener( object: SearchView.OnQueryTextListener{
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

    fun buttonStatusCheck() {
        loginButton.isEnabled =
            loginEditText.text.isNotEmpty() && pasEditText.text.isNotEmpty() && agreementCheckBox.isChecked
    }

    fun onAgreement(view: View) {
        buttonStatusCheck()
    }

    fun onLogin(view: View) {
        val bar = ProgressBar(this).apply {
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }
        parentContainer.addView(bar)
        viewDisable(false)
        Handler().postDelayed({
            viewDisable(true)
            toastShow((R.string.success_login).toString())
            parentContainer.removeView(bar)
        }, 2000)
    }

    private fun viewDisable(onOff: Boolean) {
        for (b in arrayOf(loginEditText, pasEditText, agreementCheckBox, loginButton)) {
            b.isEnabled = onOff
        }
    }

    private fun toastShow(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

}
