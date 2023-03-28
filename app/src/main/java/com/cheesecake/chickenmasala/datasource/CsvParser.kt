package com.cheesecake.chickenmasala.datasource

import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.model.MealCourse

class CsvParser {

    fun parseMeal(tokenizedList: List<String>): Meal {
        return Meal(
            translatedRecipeName = tokenizedList[Constants.ColumnIndex.TRANSLATED_RECIPE_NAME],
            url = tokenizedList[Constants.ColumnIndex.URL],
            imageUrl = tokenizedList[Constants.ColumnIndex.IMAGE_URL],
            cuisine = tokenizedList[Constants.ColumnIndex.CUISINE],
            translatedInstructions = tokenizedList[Constants.ColumnIndex.TRANSLATED_INSTRUCTIONS].replace("\n", "").replace("\"", "").split(".").dropLast(1),
            TotalTimeInMinutes = tokenizedList[Constants.ColumnIndex.TOTAL_TIME_IN_MINUTES].toInt(),
            cleanedIngredients = tokenizedList[Constants.ColumnIndex.CLEANED_INGREDIENTS].split("-"),
            translatedIngredients = tokenizedList[Constants.ColumnIndex.TRANSLATED_INGREDIENTS].split("-"),
            ingredientCount = tokenizedList[Constants.ColumnIndex.INGREDIENT_COUNT].trim().toInt(),
            course = getMealCourse(tokenizedList[Constants.ColumnIndex.TRANSLATED_RECIPE_NAME])

        )
    }

    private fun getMealCourse(mealName: String): MealCourse? {
        return when {
            mealName.contains("Soup") -> MealCourse.SOUPS
            mealName.contains("Spicy") -> MealCourse.SPICY
            mealName.contains("chicken") -> MealCourse.CHICKEN
            mealName.contains("Vegetables") -> MealCourse.VEGETABLES
            mealName.contains("Masala") -> MealCourse.MASALA
            mealName.contains("Cake") -> MealCourse.CAKES
            mealName.contains("Breakfast") -> MealCourse.BREAKFAST


            else -> null
        }
    }





}
