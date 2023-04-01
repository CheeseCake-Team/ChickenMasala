package com.cheesecake.chickenmasala.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.cheesecake.chickenmasala.R
import kotlinx.parcelize.Parcelize

enum class MealCourse(val courseName: String, @DrawableRes val imageResourceId: Int)  {
    SOUPS("soups", R.drawable.soup_category),
    SPICY("spicy", R.drawable.spicy_category),
    BAKED("baked", R.drawable.baked),
    VEGETABLES("vegetables", R.drawable.vegetables_category),
    MASALA("masala", R.drawable.masala_category),
    BREAKFAST("breakfast", R.drawable.breakfast_category),
    CAKES("cakes", R.drawable.cake_category),
    GRILLED("grilled", R.drawable.food_image_thirteen),
    BREAD("bread", R.drawable.food_image_nine),
    RICE("rice", R.drawable.rice),
    JUICE("juice", R.drawable.juice)
}
