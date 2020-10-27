package com.example.androidhomework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.list_fragment.*


class ListFragment : Fragment() {

private var list = arrayOf("первый", "второй", "третий", "четертый","пятый","шестой")

    companion object {
        fun newInstance() = ListFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.list_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

      val arrayAdapter = ArrayAdapter(this.requireContext(), android.R.layout.simple_list_item_1, list)
        listViewFragment.setOnItemClickListener(OnItemClickListener { parent, itemClicked, position, id ->
            parentFragmentManager.beginTransaction().replace(R.id.main_Fragment, DetailFragment.newInstance(list[position])).addToBackStack(list[position]).setTransition(
                FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit()
        })

      listViewFragment.adapter = arrayAdapter
    }
}
