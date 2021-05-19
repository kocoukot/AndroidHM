package com.example.androidhomework.threading

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.fragment_threading.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun EditText.textChangedFlow(): Flow<String> {
    return callbackFlow {
        val textChangedListener = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                sendBlocking(s?.toString().orEmpty())
            }

        }
        this@textChangedFlow.addTextChangedListener(textChangedListener)
        awaitClose {
            Log.d("module30", "close flow")
            this@textChangedFlow.removeTextChangedListener(textChangedListener)
        }
    }
}

fun RadioGroup.selectedRadioChangedFlow(): Flow<String> {

    return callbackFlow {
        val checkedChangeListener = RadioGroup.OnCheckedChangeListener { _, checkedId ->
            findViewById<RadioButton>(checkedId)?.apply {
                sendBlocking(this.text.toString())
            }
        }

        setOnCheckedChangeListener(checkedChangeListener)
        awaitClose {
            setOnCheckedChangeListener(null)
            Log.d("module30", "close flow")
        }
    }
}