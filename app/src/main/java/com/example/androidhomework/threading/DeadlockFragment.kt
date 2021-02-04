package com.example.androidhomework.threading

import android.os.Bundle
import android.provider.Telephony
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.androidhomework.R
import kotlinx.android.synthetic.main.fragment_deadlock.*

class DeadlockFragment : Fragment(R.layout.fragment_deadlock) {

    var value = 0

    override fun onResume() {
        super.onResume()
        val unit1 = Unit("First")
        val unit2 = Unit("Second")

        val thread1 = Thread {
            unit1.incrementNum(unit2, value)
        }

        val thread2 = Thread {
            unit2.incrementNum(unit1, value)
        }

        thread1.start()
        thread2.start()
    }


    data class Unit(
        val name: String

    ) {
//@Synchronized
        fun incrementNum(unit: Unit, value: Int) {
            var v: Int
            synchronized(this) {
                v = value + 1
                Log.d(
                    "Unit",
                    "$name увеличивает на 1 значение $v передает ${unit.name} на потоке ${Thread.currentThread().name}"
                )
                Thread.sleep(500)

            }
            unit.incrementNum(this, v)
        }
    }
}