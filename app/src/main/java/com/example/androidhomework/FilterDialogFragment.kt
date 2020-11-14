package com.example.androidhomework

import android.app.Dialog
import android.os.Bundle
import android.text.BoringLayout
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class FilterDialogFragment(
    private var checkedItems: BooleanArray
) : DialogFragment() {
private var tempBool = arrayListOf<Boolean>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val listTest = ArrayList<String>()
        for (i in 0 until checkedItems.count()){
            tempBool.add(checkedItems[i])
        }

        for (i in ArticleTag.values().indices) {
            listTest.add(i, ArticleTag.values()[i].articleTag)
        }

        return AlertDialog.Builder(requireContext())
            .setTitle("Articles")
            .setMultiChoiceItems(listTest.toTypedArray(), tempBool.toBooleanArray()) { _, which, isChecked ->
               tempBool[which] = isChecked
            }

            .setPositiveButton("Применить") { _, _ ->
                checkedItems = tempBool.toBooleanArray()
                (activity as MainActivity).onAcceptFilter(checkedItems)
            }

            .setNegativeButton("Отмена") { dialog, _ ->
                dialog.cancel()

            }.create()
    }
}