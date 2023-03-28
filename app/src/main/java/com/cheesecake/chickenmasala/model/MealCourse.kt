package com.cheesecake.chickenmasala.model

import android.os.Parcelable
import com.cheesecake.chickenmasala.R
import kotlinx.parcelize.Parcelize

@Parcelize
enum class MealCourse(val courseName: String, val imageResourceId: Int) : Parcelable {
    SOUPS("Soups", R.drawable.soup_category),
    SPICY("Spicy", R.drawable.spicy_category),
    CHICKEN("chicken", R.drawable.food_image_six),
    VEGETABLES("Vegetables", R.drawable.vegetables_category),
    MASALA("Masala", R.drawable.masala_category),
    BREAKFAST("Breakfast", R.drawable.breakfast_category),
    CAKES("Cake", R.drawable.cake_category),
}
