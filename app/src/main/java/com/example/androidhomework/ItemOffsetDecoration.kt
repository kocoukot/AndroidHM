package com.example.androidhomework

import android.content.Context
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.Display
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemOffsetDecoration(private val context: Context): RecyclerView.ItemDecoration() {


    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val offSet = 5.fromDptoPixels(context)
        with(outRect){
            left = offSet
            right = offSet
            top = offSet
            bottom = offSet
        }
    }

    private fun Int.fromDptoPixels(context: Context): Int{
        val density = context.resources.displayMetrics.densityDpi
        val pixelsInDp = density / DisplayMetrics.DENSITY_DEFAULT
        return  this * pixelsInDp
    }

}