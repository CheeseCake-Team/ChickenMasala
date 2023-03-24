package com.cheesecake.chickenmasala.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meal(
    val translatedRecipeName: String,
    val TotalTimeInMinutes: Int,
    val cuisine: String,
    val translatedInstructions: List<String>,
    val url: String,
    val cleanedIngredients: List<String>,
    val translatedIngredients: List<String>,
    val imageUrl: String,
    val ingredientCount: Int,
    val course: MealCourse?
) : Parcelable