package com.cheesecake.chickenmasala.model

import android.os.Parcelable
import com.cheesecake.chickenmasala.R
import kotlinx.parcelize.Parcelize

@Parcelize
enum class MealCourse( val CourseName: String, val imageResourceId: Int) : Parcelable {
    SOUPS("Soups", R.drawable.food_image_fourteen),
    SPICY("Spicy", R.drawable.food_image_fourteen),
    SALADS("Salads", R.drawable.food_image_fourteen),
    VEGETABLES("Vegetables", R.drawable.food_image_fourteen),
    SIDE_DISHES("Side Dishes", R.drawable.food_image_eight),
    BREAKFAST("Breakfast", R.drawable.food_image_nine),
    DRINKS("Drinks", R.drawable.food_image_fourteen),
    DESERT("Desert", R.drawable.food_image_fourteen),}
