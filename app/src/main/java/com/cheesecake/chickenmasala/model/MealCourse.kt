package com.cheesecake.chickenmasala.model

import android.os.Parcelable
import com.cheesecake.chickenmasala.R
import kotlinx.parcelize.Parcelize

@Parcelize
enum class MealCourse(val courseName: String, val imageResourceId: Int) : Parcelable {
    SOUPS("Soups", R.drawable.soup_category),
    SPICY("Spicy", R.drawable.spicy_category),
    SALADS("Salads", R.drawable.salad_category),
    VEGETABLES("Vegetables", R.drawable.vegetables_category),
    SIDE_DISHES("Side Dishes", R.drawable.side_dish_category),
    BREAKFAST("Breakfast", R.drawable.breakfast_category),
    DRINKS("Drinks", R.drawable.drinks),
    DESERT("Desert", R.drawable.desert),}
