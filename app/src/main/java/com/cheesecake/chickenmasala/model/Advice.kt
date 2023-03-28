package com.cheesecake.chickenmasala.model

import androidx.annotation.DrawableRes

data class Advice(
    val title: String,
    val body: String,
    @DrawableRes val imageResourceId: Int
)