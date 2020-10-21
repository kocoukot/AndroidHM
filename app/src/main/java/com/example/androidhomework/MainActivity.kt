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
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    private var state: FormState = FormState(valid = true, message = "")
    private val KEY_PARC = "ParcKey"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.println(Log.VERBOSE,"Lifecircle", "Created")

        initToolBar()
        Glide.with(this)
            .load("https://img.drive.ru/i/0/597705fdec05c4b315000004.jpg")
            .into(imageViewHead)

        if (savedInstanceState != null){
            state = savedInstanceState.getParcelable<FormState>(KEY_PARC) ?: error("Unexpected state")
            errorUpdate()
        }

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

    fun buttonStatusCheck() {
        if (loginEditText.text.isNotEmpty() && pasEditText.text.isNotEmpty() && agreementCheckBox.isChecked && Patterns.EMAIL_ADDRESS.matcher(loginEditText.text).matches()){
            state.valid = true
            loginButton.isEnabled= true
            state.message = ""
        } else {
            loginButton.isEnabled= false
            state.valid = false
            state.message = "Не введен логин и(или) пароль и(или) нет согласия"
        }

    }

    private fun errorUpdate(){
        textView2.text = state.message
    }

    fun onAgreement(view: View) {
        buttonStatusCheck()
    }

    fun onLogin(view: View) {
        val activityClass = SecondActivity::class.java
        val intent = Intent(this, activityClass)

        buttonStatusCheck()
        errorUpdate()
        if (state.valid) {
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_PARC, state)
    }


    private fun viewDisable(onOff: Boolean) {
        for (b in arrayOf(loginEditText, pasEditText, agreementCheckBox, loginButton)) {
            b.isEnabled = onOff
        }
    }

    private fun toastShow(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        this.finish()
        Log.println(Log.ERROR,"Lifecircle", "Paused")
    }

}
