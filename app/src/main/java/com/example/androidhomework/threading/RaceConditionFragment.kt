package com.example.androidhomework.threading

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.androidhomework.R
import kotlinx.android.synthetic.main.fragment_race_condition.*

class RaceConditionFragment : Fragment(R.layout.fragment_race_condition) {



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        buttonsInit()

    }

    private fun buttonsInit() {

        unsyncButton.setOnClickListener {
            var value = 0
            val threadsCount = threadsAmountEditText.text.toString().toInt()
            val incrementAmount = incrementAmountEditText.text.toString().toInt()
            val expectedValue = value + threadsCount * incrementAmount
            val startTime = System.currentTimeMillis()
            (0 until threadsCount).map {
                Thread {
                    for (i in 0 until incrementAmount) {
                        value++
                    }
                }.apply {
                    start()
                }
            }.map {
                it.join()
            }
            unsyncResultTextView.text =
                "value = $value \nexpected = $expectedValue \ntime = ${System.currentTimeMillis() - startTime}"
        }


        syncButton.setOnClickListener {
            var value = 0
            val threadsCount = threadsAmountEditText.text.toString().toInt()
            val incrementAmount = incrementAmountEditText.text.toString().toInt()
            val expectedValue = value + threadsCount * incrementAmount
            val startTime = System.currentTimeMillis()
            (0 until threadsCount).map {
                Thread {
                    synchronized(this) {
                        for (i in 0 until incrementAmount) {
                            value++
                        }
                    }
                }.apply {
                    start()
                }
            }.map {
                it.join()
            }
            syncResultTextView.text =
                "value = $value \nexpected = $expectedValue \ntime = ${System.currentTimeMillis() - startTime}"
        }

    }

}