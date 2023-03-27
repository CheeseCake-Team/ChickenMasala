package com.cheesecake.chickenmasala.model

import android.os.Parcelable
import com.cheesecake.chickenmasala.R
import kotlinx.parcelize.Parcelize

@Parcelize
enum class MealCourse( val CourseName: String, val imageResourceId: Int) : Parcelable {
    SOUPS("Soups", R.drawable.image),
    SPICY("Spicy", R.drawable.spice_),
    SALADS("Salads", R.drawable.),
    VEGETABLES("Vegetables", R.drawable.),
    SIDE_DISHES("Side Dishes", R.drawable.side_dishes),
    BREAKFAST("Breakfast", R.drawable.),
    DRINKS("Drinks", R.drawable.drinks),
    DESERT("Desert", R.drawable.desert),
}