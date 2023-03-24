package com.cheesecake.chickenmasala.datasource

import com.cheesecake.chickenmasala.model.Meal
import com.cheesecake.chickenmasala.model.MealCourse

class CsvParser {
    private val tokenizedList: MutableList<String> = mutableListOf()
    private val meals: MutableList<Meal> = mutableListOf()

    fun parseTotalColumns(csvLine: String) {
        if (csvLine.contains(",")) {
            val tokenizedList1 = csvLine.split(",").toMutableList()
            if (tokenizedList1.first().contains("\"")) {
                appendToLastIndexInTokenizedList(tokenizedList1.first())
                tokenizedList1.removeFirst()
            }
            tokenizedList.addAll(tokenizedList1)
            if (tokenizedList.size >= ColumnIndex.TOTAL_COLUMNS) {
                val mealTokens = tokenizedList.take(ColumnIndex.TOTAL_COLUMNS)
                val meal = parseMeal(mealTokens)
                meals.add(meal)
                tokenizedList.removeAll(mealTokens)
            }
        } else {
            appendToLastIndexInTokenizedList(csvLine)
        }
    }

    private fun appendToLastIndexInTokenizedList(string: String) {
        val lastToken = tokenizedList.last()
        val newToken = "$lastToken $string"
        tokenizedList[tokenizedList.lastIndex] = newToken
    }

    fun getMeals(): List<Meal> {
        return meals.toList()
    }

    private fun parseMeal(tokenizedList: List<String>): Meal {
        return Meal(
            translatedRecipeName = tokenizedList[ColumnIndex.TRANSLATED_RECIPE_NAME],
            url = tokenizedList[ColumnIndex.URL],
            imageUrl = tokenizedList[ColumnIndex.IMAGE_URL],
            cuisine = tokenizedList[ColumnIndex.CUISINE],
            translatedInstructions = tokenizedList[ColumnIndex.TRANSLATED_INSTRUCTIONS].split("."),
            TotalTimeInMinutes = tokenizedList[ColumnIndex.TOTAL_TIME_IN_MINUTES].toInt(),
            cleanedIngredients = tokenizedList[ColumnIndex.CLEANED_INGREDIENTS].split("-"),
            translatedIngredients = tokenizedList[ColumnIndex.TRANSLATED_INGREDIENTS].split("-"),
            ingredientCount = tokenizedList[ColumnIndex.INGREDIENT_COUNT].toInt(),
            course = getMealCourse(tokenizedList[ColumnIndex.TRANSLATED_RECIPE_NAME])

        )
    }

    private fun getMealCourse(mealName: String): MealCourse? {
        return when {
            mealName.contains("Soup") -> MealCourse.SOUP
            mealName.contains("Appetizer") -> MealCourse.APPETIZER
            mealName.contains("Spicy") -> MealCourse.SPICY
            else -> null
        }
    }


    object ColumnIndex {
        const val TRANSLATED_RECIPE_NAME = 0
        const val TRANSLATED_INGREDIENTS = 1
        const val TOTAL_TIME_IN_MINUTES = 2
        const val CUISINE = 3
        const val TRANSLATED_INSTRUCTIONS = 4
        const val URL = 5
        const val CLEANED_INGREDIENTS = 6
        const val IMAGE_URL = 7
        const val INGREDIENT_COUNT = 8
        const val TOTAL_COLUMNS = 9
    }
}
