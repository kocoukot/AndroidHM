package com.example.androidhomework

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.blank_fragment.*


class LoginFragment : Fragment(R.layout.blank_fragment) {
    private var state: FormState = FormState(valid = true, message = "")
    companion object {
        fun newInstance() = LoginFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.blank_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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

        loginButton.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.frame_Layout, MainFragment())
                .commit()
        }

        agreementCheckBox.setOnClickListener {
            buttonStatusCheck()
        }

    }

    private fun errorUpdate(){
        textView2.text = state.message
    }

    fun buttonStatusCheck() {
        if (loginEditText.text.isNotEmpty() && pasEditText.text.isNotEmpty() && agreementCheckBox.isChecked) {// && Patterns.EMAIL_ADDRESS.matcher(loginEditText.text).matches()){
            state.valid = true
            loginButton.isEnabled = true
            state.message = ""
        } else {
            loginButton.isEnabled = false
            state.valid = false
            state.message = "Не введен логин и(или) пароль и(или) нет согласия"
        }
    }
}
