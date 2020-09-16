package com.example.androidhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
            toastShow(R.string.success_login)
            parentContainer.removeView(bar)
        }, 2000)
    }

    private fun viewDisable(onOff: Boolean) {
        for (b in arrayOf(loginEditText, pasEditText, agreementCheckBox, loginButton)) {
            b.isEnabled = onOff
        }
    }

    private fun toastShow(text: Int) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

}
