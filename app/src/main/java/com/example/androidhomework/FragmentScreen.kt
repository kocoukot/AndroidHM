package com.example.androidhomework

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class FragmentScreen(
    @StringRes val articeTitle: Int,
    @StringRes val textRes: Int,
    @DrawableRes val image: Int,
    val tag: ArticleTag,
    var badgeAmount: Int
)