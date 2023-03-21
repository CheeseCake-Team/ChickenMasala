package com.cheesecake.chickenmasala.model

data class Meal(
    val translatedRecipeName: String,
    val TotalTimeInMinutes: Int,
    val cuisine: String,
    val translatedInstructions: List<String>,
    val url: String,
    val cleanedIngredients:List<String>,
    val translatedIngredients: List<String>,
    val imageUrl: String,
    val ingredientCount:Int,
) {
    val course: MealCourse? = when (true) {
        translatedRecipeName.contains("Soup") -> MealCourse.SOUP
        translatedRecipeName.contains("Appetizer") -> MealCourse.APPETIZER
        translatedRecipeName.contains("Spicy") -> MealCourse.SPICY
        else -> null
    }
}