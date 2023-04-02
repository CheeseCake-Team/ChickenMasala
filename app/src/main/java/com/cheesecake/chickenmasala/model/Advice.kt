package com.cheesecake.chickenmasala.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Advice(
    @StringRes val title: Int,
    @StringRes val body: Int,
    @DrawableRes val imageResourceId: Int
)