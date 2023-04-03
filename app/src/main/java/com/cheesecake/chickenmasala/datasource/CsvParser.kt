package com.cheesecake.chickenmasala.datasource

import com.cheesecake.chickenmasala.model.Constants
import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.model.MealCourse

class CsvParser {

    fun parseMeal(tokenizedList: List<String>): Meal {
        return Meal(
            translatedRecipeName = tokenizedList[Constants.ColumnIndex.TRANSLATED_RECIPE_NAME],
            translatedIngredients = getTranslatedIngredientsFromString(tokenizedList[Constants.ColumnIndex.TRANSLATED_INGREDIENTS]),
            TotalTimeInMinutes = tokenizedList[Constants.ColumnIndex.TOTAL_TIME_IN_MINUTES].toInt(),
            cuisine = tokenizedList[Constants.ColumnIndex.CUISINE],
            translatedInstructions = getInstructionsFromString(tokenizedList[Constants.ColumnIndex.TRANSLATED_INSTRUCTIONS]),
            url = tokenizedList[Constants.ColumnIndex.URL],
            cleanedIngredients = getCleanIngredientsFromString(tokenizedList[Constants.ColumnIndex.CLEANED_INGREDIENTS]),
            imageUrl = tokenizedList[Constants.ColumnIndex.IMAGE_URL],
            ingredientCount = tokenizedList[Constants.ColumnIndex.INGREDIENT_COUNT].trim().toInt(),
            course = getMealCourse(tokenizedList[Constants.ColumnIndex.TRANSLATED_RECIPE_NAME])
        )
    }
    private fun getMealCourse(mealName: String): MealCourse? {
        return when{
            mealName.contains("Soup") -> MealCourse.SOUPS
            mealName.contains("Spicy") -> MealCourse.SPICY
            mealName.contains("Rice") -> MealCourse.RICE
            mealName.contains("Vegetables") -> MealCourse.VEGETABLES
            mealName.contains("Masala") -> MealCourse.MASALA
            mealName.contains("Cake") -> MealCourse.CAKES
            mealName.contains("Breakfast") -> MealCourse.BREAKFAST
            mealName.contains("Baked") -> MealCourse.BAKED
            mealName.contains("Grilled") -> MealCourse.GRILLED
            mealName.contains("Juice") -> MealCourse.JUICE
            else -> null
        }
    }
    private fun getTranslatedIngredientsFromString(ingredients: String) =
        ingredients.replace(" - ", " ").split("-").filter { it.length > 1 }

    private fun getCleanIngredientsFromString(ingredients: String) =
        ingredients.split("-")

    private fun getInstructionsFromString(ingredients: String) =
        ingredients.removeSurrounding("\"").split(".").toMutableList().apply { removeLast() }

}
