package com.cheesecake.chickenmasala.model

import androidx.annotation.DrawableRes
import com.cheesecake.chickenmasala.R

enum class MealCourse(val courseName: String, @DrawableRes val imageResourceId: Int)  {
    SOUPS("Soups", R.drawable.soup_category),
    SPICY("Spicy", R.drawable.spicy_category),
    RICE("Rice", R.drawable.rice),
    VEGETABLES("Vegetables", R.drawable.vegetables_category),
    MASALA("Masala", R.drawable.masala_category),
    BREAKFAST("Breakfast", R.drawable.breakfast_category),
    CAKES("Cakes", R.drawable.cake_category),
    BAKED("Baked", R.drawable.food_image_nine),
    GRILLED("Grilled", R.drawable.food_image_thirteen),
    JUICE("Juice", R.drawable.juice),
}
