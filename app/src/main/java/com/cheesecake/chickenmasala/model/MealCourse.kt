package com.cheesecake.chickenmasala.model

import android.os.Parcelable
import com.cheesecake.chickenmasala.R
import kotlinx.parcelize.Parcelize

@Parcelize
enum class MealCourse( val CourseName: String, val imageResourceId: Int) : Parcelable {
    SOUPS("Soups", R.drawable.image),
    SPICY("Spicy", R.drawable.spice_),
    NORTH_INDIA("North India", R.drawable.north_india),
    SOUTH_INDIA("South India", R.drawable.south_india),
    DESERT("Desert", R.drawable.desert),
    SIDE_DISHES("Side Dishes", R.drawable.side_dishes),
    DRINKS("Drinks", R.drawable.drinks),
}