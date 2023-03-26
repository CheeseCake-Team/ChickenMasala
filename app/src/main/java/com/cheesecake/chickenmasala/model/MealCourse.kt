package com.cheesecake.chickenmasala.model

import android.os.Parcelable
import com.cheesecake.chickenmasala.R
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class MealCourse( val CourseName: String, val imageResourceId: Int) : Parcelable {
    SOUPS("Soup", R.drawable.cooking_image),
    APPETIZER("APPETIZER", R.drawable.cooking_image),
    SPICY("SPICY", R.drawable.cooking_image),
    NORTH_INDIAN("NORTH_INDIAN", R.drawable.cooking_image),
    SOUTH_INDIA("SOUTH_INDIA", R.drawable.cooking_image),
    DESERT("DESERT", R.drawable.cooking_image),
    SIDE_DISHES("SIDE_DISHES", R.drawable.cooking_image),
    DRINKS("DRINKS", R.drawable.cooking_image),
}