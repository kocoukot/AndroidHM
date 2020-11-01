package com.example.androidhomework

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {



    companion object {
        var text = ""

        fun newInstance(text: String): DetailFragment{
            this.text = text
            val fragment = DetailFragment()
            return fragment
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        retainInstance = true
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        detailInfo.text = ("info about $text")
    }

}
