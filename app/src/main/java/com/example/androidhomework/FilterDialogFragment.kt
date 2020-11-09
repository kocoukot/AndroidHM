package com.example.androidhomework

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class FilterDialogFragment(
    private var checkedItems: BooleanArray,
    private var filteredFragmentList:  List<FragmentScreen>
) : DialogFragment() {
var filteredList: MutableList<ArticleTag> = mutableListOf()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val listTest = ArrayList<String>()
        for (i in ArticleTag.values().indices) {
            listTest.add(i, ArticleTag.values()[i].articleTag)
        }

        return AlertDialog.Builder(requireContext())
            .setTitle("Articles")
            .setMultiChoiceItems(listTest.toTypedArray(), checkedItems) { _, which, isChecked ->
                checkedItems[which] = isChecked
            }

            .setPositiveButton("Применить") { _, _ ->
                for (i in checkedItems.indices){
                    if (checkedItems[i]){
                        Log.i("module15",ArticleTag.values().get(i).toString())
                        filteredList.add(ArticleTag.values().get(i))
                    }
                }
            }

            .setNegativeButton("Отмена") { dialog, _ ->
                dialog.cancel()
            }.create()
    }

}