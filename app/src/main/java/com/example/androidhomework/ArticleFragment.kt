package com.example.androidhomework

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.badge.BadgeDrawable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.blank_fragment.*
import kotlin.random.Random

//import sun.jvm.hotspot.utilities.IntArray


class ArticleFragment : Fragment(R.layout.blank_fragment) {

    companion object {
        private const val KEY_TEXT = "text"
        private const val KEY_IMAGE = "image"
        private const val KEY_ISHOWN = "shown"



        fun newInstance(
            @StringRes articeTitle: Int,
            @StringRes textRes: Int,
            @DrawableRes image: Int,
            tag: ArticleTag,
            isShown: Boolean
        ): ArticleFragment {
            return ArticleFragment().withArguments {
                putInt(KEY_TEXT, textRes)
                putInt(KEY_IMAGE, image)
                putBoolean(KEY_ISHOWN, isShown)

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.blank_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        imageViewHead.setImageResource(requireArguments().getInt(KEY_IMAGE))
        textView.setText(requireArguments().getInt(KEY_TEXT))

        generateButton.setOnClickListener {
            var randomArticle = (0..3).random()
            toastShow(randomArticle.toString())
            requireActivity().tabLayout2.getTabAt(randomArticle)?.orCreateBadge?.apply {
              if (number == 0) {
              number = 1
              } else {
                  number++
              }
                badgeGravity = BadgeDrawable.TOP_END
            }
        }
    }

    private fun toastShow(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

}
