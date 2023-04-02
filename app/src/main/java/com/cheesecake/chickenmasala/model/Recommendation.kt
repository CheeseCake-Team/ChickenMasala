package com.cheesecake.chickenmasala.model

import androidx.annotation.StringRes

data class Recommendation(
    @StringRes val title: Int,
    val listOfRecipes: List<Meal>
)