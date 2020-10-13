package com.example.androidhomework

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.util.Patterns
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_second.*
import kotlin.jvm.internal.Ref

class SecondActivity : AppCompatActivity() {
    private val requestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        handleIntentData()
    }

    fun onCall(view: View) {
        val validNum = Patterns.PHONE.matcher(editTextNumber.text).matches()
        val number = editTextNumber.text
        if (!validNum){
            toastShow("некорректный формат номера")
        } else {
            val phoneIntent = Intent(Intent.ACTION_DIAL).apply {
               data = Uri.parse("tel:$number")
            }
            if (phoneIntent.resolveActivity(packageManager) != null) {
                startActivityForResult(phoneIntent,requestCode)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode.equals(this.requestCode)){
            if (resultCode == Activity.RESULT_CANCELED){
                toastShow("canceled")
            } else {
                toastShow("OKed")
            }
        }
    }

    private fun handleIntentData(){
        intent.data?.host?.let{ hostString ->
            textViewURI.text = hostString
        }
        intent.data?.path?.let {pathString ->
            textViewURI.text = "${textViewURI.text}$pathString"
        }
    }

    private fun toastShow(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
